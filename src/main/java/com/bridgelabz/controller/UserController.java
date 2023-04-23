package com.bridgelabz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.dto.LoginDto;
import com.bridgelabz.dto.PostDto;
import com.bridgelabz.dto.RegisterDto;
import com.bridgelabz.model.Post;
import com.bridgelabz.service.UserServiceImps;
import com.bridgelabz.service.UserServiceWithToken;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserServiceImps userServiceImps;
	
	@Autowired
	private UserServiceWithToken userServiceWithToken;
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<Object> addUsre(@RequestBody RegisterDto registerDto){
		return this.userServiceWithToken.register(registerDto);
	}
	
	@PostMapping("/addPost/{token}")
	public ResponseEntity<List<Post>> addPost(@PathVariable String token, @RequestBody PostDto postDto){
		 return this.userServiceImps.addPost(token,postDto);
	}
	@PutMapping("/update/{token}")
	public ResponseEntity<Object> updateUser(@PathVariable String token, @RequestBody RegisterDto registerDto){
		return this.userServiceImps.updateUser(token, registerDto);
	}
	
	@GetMapping("/getDetails/{token}")
	public ResponseEntity<Object>getDetails(@PathVariable String token){
		return this.userServiceImps.getDetails(token);
	}
	@DeleteMapping("/deleted/{token}/{id}")
	public ResponseEntity<Object> removePost(@PathVariable String token, @PathVariable int id){
		return this.userServiceImps.removePost(token, id);
	}
	@PutMapping("/updatePost/{token}/{id}")
    public ResponseEntity<Object>updatePost(@PathVariable String token,@PathVariable int id ,@RequestBody PostDto postDto){
    	 return this.userServiceImps.updatePost( token,id,postDto);
    }
	@PostMapping("/login")
	public ResponseEntity<Object>login(@RequestBody LoginDto loginDto){
		return this.userServiceWithToken.login(loginDto);
	}
	@GetMapping("/logOut/{token}")
	public ResponseEntity<Object> logOut(@PathVariable String token){
		return this.userServiceImps.logOut(token);
	}
	@GetMapping("/getallPost/{token}")
	public ResponseEntity<List<Post>> getPost(@PathVariable String token){
		return this.userServiceImps.getAllPost(token);
	}
	
	@GetMapping("/archive/{token}/{id}")
	public ResponseEntity<Object> archivest(@PathVariable String token,@PathVariable int id){
		return this.userServiceImps.archive(token, id);
	}
	@GetMapping("/tarsh/{token}/{id}")
	public ResponseEntity<Object> tarsh(@PathVariable String token,@PathVariable int id){
		return this.userServiceImps.Trash(token, id);
	}
	

}
