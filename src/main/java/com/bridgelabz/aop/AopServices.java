package com.bridgelabz.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.dto.LoginDto;
import com.bridgelabz.exception.UserException;
import com.bridgelabz.model.User;
import com.bridgelabz.repository.UserRepositoty;
import com.bridgelabz.utility.TokenManger;

@Aspect
public class AopServices {
	@Autowired
	UserRepositoty userRepositoty;
	@Autowired
	TokenManger tokenManger;
	
	
	@Around(value="this(com.bridgelabz.service.UserServiceWithToken)")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("Started..Here");
		Object object =joinPoint.proceed();
		String token = (String) joinPoint.getArgs()[0];
		
		LoginDto loginDto =tokenManger.decodeToken(token);
		User user=this.userRepositoty.findByEmail(loginDto.getEmail());
		if(user.isIslogin()) {
		 return object;
		}
		else {
			throw new UserException("please login account...!");
		}
		
		
		
	}

	
	
}
