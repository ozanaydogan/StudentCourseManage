package com.management.studentmanagement.service;

import com.management.studentmanagement.common.ResponseMessage;
import com.management.studentmanagement.config.JwtService;
import com.management.studentmanagement.dto.AuthenticationRequest;
import com.management.studentmanagement.dto.AuthenticationResponse;
import com.management.studentmanagement.dto.DeleteUserResponse;
import com.management.studentmanagement.dto.RegisterRequest;
import com.management.studentmanagement.entity.Role;
import com.management.studentmanagement.entity.Student;
import com.management.studentmanagement.entity.Token;
import com.management.studentmanagement.repository.StudentRepository;
import com.management.studentmanagement.repository.TokenRepository;
import com.management.studentmanagement.entity.TokenType;
import com.management.studentmanagement.entity.User;
import com.management.studentmanagement.model.UserCreaterReturnModel;
import com.management.studentmanagement.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final StudentRepository student_Repo;

  public AuthenticationResponse register(RegisterRequest request) {
	  
	  UserCreaterReturnModel returnmodel;
	  
	  if(request.getRole().name().equals(Role.STUDENT.name())) { //buraya bakmam gerek
		  returnmodel = StudentCreater(request);
	  }
	  else {
		 returnmodel = UserCreater(request);
	  }
	  
	  //TANIMLANAN ROLLER DIŞINDA BİR ROL GELİRSE ERROR RESPONSU'NU HAZIRLAMAM GEREK
	  
	  return AuthenticationResponse.builder()
		      .accessToken(returnmodel.getJwtToken())
		      .refreshToken(returnmodel.getRefreshToken())
		      .email(returnmodel.getUser().getEmail())
		      .firstname(returnmodel.getUser().getFirstname())
		      .lastname(returnmodel.getUser().getLastname())
		      .id(returnmodel.getUser().getId().toString())
		      .role(returnmodel.getUser().getRole().name())
		      .message(ResponseMessage.SUCCESSFUL)
		      .build();
		  
	
  }
  
  private UserCreaterReturnModel UserCreater(RegisterRequest request) {
	  		User user = User.builder()
		        .firstname(request.getFirstname())
		        .lastname(request.getLastname())
		        .email(request.getEmail())
		        .password(passwordEncoder.encode(request.getPassword()))
		        .role(request.getRole())
		        .build();
		    var savedUser = repository.save(user);
		    var jwtToken = jwtService.generateToken(user);
		    var refreshToken = jwtService.generateRefreshToken(user);
		    var token = saveUserToken(savedUser, jwtToken);
		    
		    List<Token> t_list = new ArrayList<Token>();
		    t_list.add(token);
		    user.setTokens(t_list);
		    
		    UserCreaterReturnModel returnModel = new UserCreaterReturnModel(user,refreshToken,jwtToken);
		   
		return returnModel;    
  }
  
  private UserCreaterReturnModel StudentCreater(RegisterRequest request) {
		Student student = Student.builder()
				.firstname(request.getFirstname())
		        .lastname(request.getLastname())
		        .email(request.getEmail())
		        .password(passwordEncoder.encode(request.getPassword()))
		        .role(request.getRole())
		        .build();
		
	    var jwtToken = jwtService.generateToken(student);
	    var refreshToken = jwtService.generateRefreshToken(student);
	    
	    var token = Token.builder()
	    		.token(jwtToken)
	    		.tokenType(TokenType.BEARER)
	    		.expired(false)
	    		.revoked(false)
	    		.user(student)
	    		.build();
	    
	    var arr = new ArrayList<Token>();
	    arr.add(token);
	     
	    student.setTokens(arr);
	    
	    student_Repo.save(student); // Token'larda cascade'den dolayı oluştu
		    
		UserCreaterReturnModel returnModel = new UserCreaterReturnModel(student,refreshToken,jwtToken);
		   
		return returnModel;    
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
	 authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .message(ResponseMessage.SUCCESSFUL)
        .build();
  }

  private Token saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
    return token;
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response //servlet response olarak döndüm
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    
    refreshToken = authHeader.split(" ")[1].trim();
    
    if(!jwtService.extractTokenType(refreshToken).equals("REFRESH"))  // kendi olusturdugumuz custom claims'e tokenin refresh'mi access mi oldugunu belirttik. 
    	return;														//daha öncesinde access tokeni refresh token olarak görüp access tokenle yeni bir token 
    																//alınabiliyordu.bunu önledim
    
    
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.repository.findByEmail(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .message(ResponseMessage.SUCCESSFUL)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse); //servlet response olarak döndüm
      }
    }
  }

  	public DeleteUserResponse deleteUser(Integer id) {
  		
  		DeleteUserResponse resp;
  		
  		Optional<User> usr = repository.findById(id);
  		
  		if(usr.isEmpty()) {
  			resp = DeleteUserResponse.builder().message(ResponseMessage.USER_NOT_FOUND).build();
  			
  			return resp;
  		}
  		
  		User usr_ = usr.get();
  		
  		if(usr_.getRole().equals(Role.STUDENT)) {
  			
  			Student student_user = (Student)usr_;
  			
  			if(student_user.getCourses().size() != 0) {
  				
  				resp = DeleteUserResponse.builder().message(ResponseMessage.THE_STUDENT_HAS_A_COURSE_CANT_BE_DELETED+ ", " + ResponseMessage.FIRST_REMOVE_COURSES).build();
  				
  				return resp;
  			}
  			
  		}
  		
  		repository.deleteById(id); //cascade'den dolayı TOKEN'da silinir
  		
  		resp = DeleteUserResponse.builder().message(ResponseMessage.SUCCESSFUL).build();			
  		
	return resp;
	}
}
