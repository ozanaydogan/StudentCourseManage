package com.management.studentmanagement.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.management.studentmanagement.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}
