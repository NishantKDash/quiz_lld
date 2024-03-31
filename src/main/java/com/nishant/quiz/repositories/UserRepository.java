package com.nishant.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nishant.quiz.models.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
	public UserEntity findByName(String name);
}
