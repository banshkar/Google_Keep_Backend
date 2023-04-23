package com.bridgelabz.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class User {
	     
	@Id
	@GeneratedValue()
	private int id;
	private String name;
	private String email;
	private String password;
	private boolean islogin =false;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="card_id")
	private Card card;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String name, String email, String password, Card card,boolean islogin) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.card = card;
		this.islogin=islogin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	
	public boolean isIslogin() {
		return islogin;
	}
	public void setIslogin(boolean islogin) {
		this.islogin = islogin;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", islogin="
				+ islogin + ", card=" + card + "]";
	}
	

}
