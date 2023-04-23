package com.bridgelabz.dto;

import org.springframework.stereotype.Component;

import com.bridgelabz.model.Card;

@Component
public class RegisterDto {
	private String name;
	private String email;
	private String password;
//	private Card card;
	
	public RegisterDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RegisterDto(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public Card getCard() {
//		return card;
//	}
//	public void setCard(Card card) {
//		this.card = card;
//	}
//	@Override
//	public String toString() {
//		return "RegisterDto [name=" + name + ", email=" + email + ", password=" + password + ", card=" + card + "]";
//	}
	
} 
