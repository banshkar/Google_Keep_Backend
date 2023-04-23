package com.bridgelabz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.model.Post;

public interface PostRepo extends JpaRepository<Post, Integer>{
   public Post findByTitle(String title);
  
}
