package com.bridgelabz.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.bridgelabz.dto.LoginDto;
import com.bridgelabz.dto.RegisterDto;
import com.bridgelabz.exception.ReponseException;
import com.bridgelabz.exception.UserException;
import com.bridgelabz.model.Card;
import com.bridgelabz.model.User;
import com.bridgelabz.repository.CardRepo;
import com.bridgelabz.repository.PostRepo;
import com.bridgelabz.repository.UserRepositoty;
import com.bridgelabz.utility.TokenManger;

@Component
public class UserServiceWithToken {
	@Autowired
	UserRepositoty userRepositoty;
	@Autowired
	CardRepo cardRepo;
	
	@Autowired
	PostRepo postRepo;
	
	@Autowired
	ReponseException reponseException;
	@Autowired
	TokenManger tokenManger;
	@Autowired
	ModelMapper mapper;
	public ResponseEntity<Object> login(LoginDto loginDto) {
		  
		if(this.userRepositoty.findByEmail(loginDto.getEmail())==null) throw new UserException("please Register Account");
		else {
			if(this.userRepositoty.findByEmail(loginDto.getEmail()).getPassword().equals(loginDto.getPassword())) {
				User user =this.userRepositoty.findByEmail(loginDto.getEmail());
				user.setIslogin(true);
				this.userRepositoty.save(user);
				String token =this.tokenManger.generateToken(loginDto);
				return reponseException.getresponse("Login", token);
			}
			else {
				throw new UserException("please Enter Right Password");                                    
			}
		}
	   
	}
	public ResponseEntity<Object> register(RegisterDto registerDto) {
	      
		if(this.userRepositoty.findByEmail(registerDto.getEmail())==null) {
			User user =mapper.map(registerDto, User.class);
			Card card =new Card();
			user.setCard(card);
			this.userRepositoty.save(user);
			return new ResponseEntity<Object>("register successfully",HttpStatus.OK);
		}
		 
       throw new UserException("This email already Register");
	}

}
