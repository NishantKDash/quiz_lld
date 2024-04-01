package com.nishant.quiz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishant.quiz.exceptions.UserAlreadyExistsException;
import com.nishant.quiz.models.UserEntity;
import com.nishant.quiz.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserEntity createUser(String name) throws Exception {
		UserEntity user = userRepository.findByName(name);
		if (user != null)
			throw new UserAlreadyExistsException("The user with name :" + name + "already exists");

		UserEntity newUser = new UserEntity();
		newUser.setName(name);
		userRepository.save(newUser);
		return newUser;

	}

	public List<UserEntity> getUsers() {

		return userRepository.findAll();
	}
}
