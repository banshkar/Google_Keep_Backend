package com.bridgelabz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.model.Card;

public interface CardRepo extends JpaRepository<Card, Integer> {

}
