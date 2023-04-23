package com.bridgelabz.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bridgelabz.dto.LoginDto;
import com.bridgelabz.dto.PostDto;
import com.bridgelabz.dto.RegisterDto;
import com.bridgelabz.exception.ReponseException;
import com.bridgelabz.exception.UserException;
import com.bridgelabz.model.Card;
import com.bridgelabz.model.Post;
import com.bridgelabz.model.User;
import com.bridgelabz.repository.CardRepo;
import com.bridgelabz.repository.PostRepo;
import com.bridgelabz.repository.UserRepositoty;
import com.bridgelabz.utility.TokenManger;

@Service
public class UserServiceWithOutToken implements UserServiceImps{
	
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

	@Override
	public ResponseEntity<List<Post>> addPost(String token,PostDto postDto) {
		LoginDto loginDto =this.tokenManger.decodeToken(token);
		String email=loginDto.getEmail();
		 if(this.userRepositoty.findByEmail(email)==null) throw new UserException("this email does not exist");
		 else {
				 Card card =this.userRepositoty.findByEmail(email).getCard();
				 Post post =mapper.map(postDto, Post.class);
				 Optional<Post>newPost =card.getPost().stream().filter(e->e.getTitle().equals(post.getTitle())).findAny();
				 if(newPost.isPresent()) {
					 throw new UserException("Please Change tilte ,This Title Name already Exist");
				 }
				 else {
					 card.getPost().add(post);
					 this.cardRepo.save(card);
					 List<Post>listCard =this.userRepositoty.findByEmail(email).getCard().getPost();
					 return new ResponseEntity<List<Post>>(listCard,HttpStatus.OK);
				 }
		 }
	
	}

	@Override
	public ResponseEntity<Object> updateUser(String token, RegisterDto registerDto) {
		LoginDto loginDto =this.tokenManger.decodeToken(token);
		String email=loginDto.getEmail();
		if(this.userRepositoty.findByEmail(email)!=null) {
			int id =this.userRepositoty.findByEmail(email).getId();
			User user =mapper.map(registerDto, User.class);
			user.setId(id);
			this.userRepositoty.save(user);
			return reponseException.getresponse("update User Successfully", user);
		}
		
	   throw new UserException("This User does not exist");
	   }

	@Override
	public ResponseEntity<Object> getDetails(String token) {
		LoginDto loginDto =this.tokenManger.decodeToken(token);
		String email=loginDto.getEmail();
		if(this.userRepositoty.findByEmail(email)!=null) {
		  User user=this.userRepositoty.findByEmail(email);
			return reponseException.getresponse(" User Details ...", user);
		}
	
	   throw new UserException("This User does not exist");
	   }
	

	@Override
	public ResponseEntity<Object> removePost(String token, int id) {
		LoginDto loginDto =this.tokenManger.decodeToken(token);
		String email=loginDto.getEmail();
		if(this.userRepositoty.findByEmail(email)!=null) {
			 List<Post>postList=this.userRepositoty.findByEmail(email).getCard().getPost();
			  User user=this.userRepositoty.findByEmail(email);
			  int idUser=this.userRepositoty.findByEmail(email).getId();
			  System.out.println("before"+postList);
				Iterator<Post>postItr =postList.iterator();
				while(postItr.hasNext()) {
					Post post =postItr.next();
					if(post.getId()==id) {
				       postItr.remove();
				       user.setId(idUser);
				       user.getCard().setPost(postList);
				       this.userRepositoty.save(user);
				       this.postRepo.deleteById(id);
				       return new ResponseEntity<Object>("deleted",HttpStatus.OK);
					}
					  System.out.println("after"+postList);
				     
				     
				}
				return new ResponseEntity<Object>("deleted notes",HttpStatus.OK);
		
		}
	
	   throw new UserException("This User does not exist");
	}

	@Override
	public ResponseEntity<Object> updatePost(String token, int id, PostDto postDto) {
	   if(this.postRepo.findById(id).isPresent()) {
		   Post post =this.postRepo.findById(id).get();
		     Post postUpdate =mapper.map(postDto, Post.class);
		     postUpdate.setId(id);
		     this.postRepo.save(postUpdate);
		     return new ResponseEntity<Object>("update Post Suceessfully",HttpStatus.OK);
	   }
	   else {
		   throw new UserException("this id does not exist");
	   }
	      
	}

	

	@Override
	public ResponseEntity<Object> logOut(String token) {
		    LoginDto loginDto =this.tokenManger.decodeToken(token);
		    String email=loginDto.getEmail();
			User user =this.userRepositoty.findByEmail(email);
			user.setIslogin(false);
			this.userRepositoty.save(user);
			return new ResponseEntity<Object>("LogOut",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Post>> getAllPost(String token) {
		LoginDto loginDto =this.tokenManger.decodeToken(token);
		String email=loginDto.getEmail();
		if(this.userRepositoty.findByEmail(email)!=null) {
		  User user=this.userRepositoty.findByEmail(email);
		  List<Post>listPost=user.getCard().getPost();
			return new ResponseEntity<List<Post>>(listPost,HttpStatus.OK); 
		}
	
	   throw new UserException("This User does not exist");
	   }

	@Override
	public ResponseEntity<Object> archive(String token, int id) {
		LoginDto loginDto =this.tokenManger.decodeToken(token);
		String email=loginDto.getEmail();
		if(this.userRepositoty.findByEmail(email)!=null) {
			 List<Post>postList=this.userRepositoty.findByEmail(email).getCard().getPost();
			  User user=this.userRepositoty.findByEmail(email);
			  System.out.println("before"+postList);
				Iterator<Post>postItr =postList.iterator();
				while(postItr.hasNext()) {
					Post post =postItr.next();
					if(post.getId()==id) {
					        post.setId(id);
					        post.setArchive(true);
					        this.postRepo.save(post);
				        return new ResponseEntity<Object>("archive",HttpStatus.OK);
					}
					
				}
		
		}
	
	   throw new UserException("This User does not exist");
	}
	@Override
	public ResponseEntity<Object> Trash(String token, int id) {
		LoginDto loginDto =this.tokenManger.decodeToken(token);
		String email=loginDto.getEmail();
		if(this.userRepositoty.findByEmail(email)!=null) {
			 List<Post>postList=this.userRepositoty.findByEmail(email).getCard().getPost();
			  User user=this.userRepositoty.findByEmail(email);
			  System.out.println("before"+postList);
				Iterator<Post>postItr =postList.iterator();
				while(postItr.hasNext()) {
					Post post =postItr.next();
					if(post.getId()==id) {
					        post.setId(id);
					        post.setTrash(true);
					        this.postRepo.save(post);
				        return new ResponseEntity<Object>("trash",HttpStatus.OK);
					}
					
				}
		
		}
	
	   throw new UserException("This User does not exist");
	}
	

}
