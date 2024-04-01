package com.nishant.quiz.controllers;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nishant.quiz.Ansi;
import com.nishant.quiz.models.UserEntity;
import com.nishant.quiz.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	public void createUser() {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println(Ansi.ANSI_RED + "Please enter your name" + Ansi.ANSI_RESET);
			String name = scanner.nextLine();
			UserEntity user = userService.createUser(name);
			System.out.println(Ansi.ANSI_GREEN + "User created with id : " + user.getId() + Ansi.ANSI_RESET);
		} catch (Exception e) {
			System.out.println(Ansi.ANSI_RED + "Error : " + e.getMessage() + Ansi.ANSI_RESET);
		}
	}

	public List<UserEntity> getUsers() {
		try {
            List<UserEntity> users = userService.getUsers();
			return users;
		} catch (Exception e) {
			System.out.println("Unable to fetch users. Please try again later");
		}
		return null;
	}
}
