package com.bridgelabz.utility;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.bridgelabz.dto.LoginDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenManger  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String secretkey ="sdsdsahdksadhsiaudhasi";
	
	public String generateToken(LoginDto loginDto) {
		Map<String, Object>map =new HashMap<>();
		map.put("email", loginDto.getEmail());
		map.put("password", loginDto.getPassword());
		String token = Jwts.builder().setClaims(map).setSubject(loginDto.getEmail()).signWith(SignatureAlgorithm.HS256, secretkey).compact();

	  return token;
	}
  public LoginDto decodeToken(String token) {
	  final Claims claims =Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
	  LoginDto  loginDto =new LoginDto();
	  String email =(String) claims.get("email");
	  String password  =(String) claims.get("password");
	  loginDto.setEmail(email);
	  loginDto.setPassword(password);
	  return loginDto;
	  
  }
}
