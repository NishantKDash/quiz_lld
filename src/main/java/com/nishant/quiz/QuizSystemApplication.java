package com.nishant.quiz;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nishant.quiz.controllers.QuestionController;
import com.nishant.quiz.controllers.UserController;

@SpringBootApplication
public class QuizSystemApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(QuizSystemApplication.class, args);
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println(
					"Welcome to quiz system  " + " 1 -> Play   2 -> Create user  3 -> Create question");
			System.out.println();
			int option = Integer.valueOf(sc.nextLine());
			if (option == 3) {
				QuestionController controller = context.getBean(QuestionController.class);
				controller.createQuestion();
			}
			if(option == 2)
			{
				UserController userController = context.getBean(UserController.class);
				userController.createUser();
			}
		}
	}
	

}
