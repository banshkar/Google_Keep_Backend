 package com.bridgelabz.model;

import java.util.List;
import java.util.TreeMap;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Post> post;
	public Card() {
		super();
	}
	public Card(int id, List<Post> post) {
		super();
		this.id = id;
		this.post = post;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Post> getPost() {
		return post;
	}
	public void setPost(List<Post> post) {
		this.post = post;
	}
	@Override
	public String toString() {
		return "Card [id=" + id + ", post=" + post + "]";
	}
	

}
