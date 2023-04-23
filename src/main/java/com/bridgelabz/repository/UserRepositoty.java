package com.bridgelabz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.model.User;

public interface UserRepositoty extends JpaRepository<User, Integer>{
   
	public User findByEmail(String email);
}
