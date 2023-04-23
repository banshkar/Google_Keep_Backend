package com.bridgelabz.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bridgelabz.dto.LoginDto;
import com.bridgelabz.dto.PostDto;
import com.bridgelabz.dto.RegisterDto;
import com.bridgelabz.model.Post;

public interface UserServiceImps  {


	public ResponseEntity<List<Post>> addPost(String email,PostDto postDto);
	public ResponseEntity<Object> updateUser(String token,RegisterDto registerDto);
	public ResponseEntity<Object> getDetails(String token);
	public ResponseEntity<Object> removePost(String token,int id);
	public ResponseEntity<Object> updatePost(String token,int id,PostDto postDto);
	public ResponseEntity<Object> logOut(String token);
	public ResponseEntity<List<Post>> getAllPost(String email);
	public ResponseEntity<Object> archive(String token,int id);
	public ResponseEntity<Object> Trash(String token, int id);

}
