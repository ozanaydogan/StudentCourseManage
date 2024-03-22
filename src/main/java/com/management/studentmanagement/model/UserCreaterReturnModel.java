package com.management.studentmanagement.model;

import com.management.studentmanagement.entity.User;

public class UserCreaterReturnModel {
	
	private User user;
	
	private String refreshToken;
	
	private String jwtToken;
	
	
	public UserCreaterReturnModel(User user, String refreshToken, String jwtToken) {
		super();
		this.user = user;
		this.refreshToken = refreshToken;
		this.jwtToken = jwtToken;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}


	
	
	

}
