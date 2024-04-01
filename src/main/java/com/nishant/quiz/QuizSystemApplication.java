package com.nishant.quiz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nishant.quiz.controllers.GameController;
import com.nishant.quiz.controllers.QuestionController;
import com.nishant.quiz.controllers.UserController;
import com.nishant.quiz.enums.GameType;
import com.nishant.quiz.models.GameEntity;
import com.nishant.quiz.models.UserEntity;

@SpringBootApplication
public class QuizSystemApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(QuizSystemApplication.class, args);
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Welcome to quiz system  " + " 1 -> Play   2 -> Create user  3 -> Create question");
			System.out.println();
			int option = Integer.valueOf(sc.nextLine());
			if (option == 3) {
				QuestionController controller = context.getBean(QuestionController.class);
				controller.createQuestion();
			}
			if (option == 2) {
				UserController userController = context.getBean(UserController.class);
				userController.createUser();
			}
			if (option == 1) {
				System.out.println("1 -> New Game    2 -> Load Game");
				int choice = Integer.valueOf(sc.nextLine());
				if (choice == 1) {
					UserController userController = context.getBean(UserController.class);
					GameController gameController = context.getBean(GameController.class);
					System.out.println("Select players");
					List<UserEntity> users = userController.getUsers();
					for (int i = 0; i < users.size(); i++)
						System.out.println((i + 1) + " -> " + users.get(i).getName());

					HashSet<UserEntity> players = new HashSet<>();
					while (true) {
						System.out.println("Enter the users sid");
						int sid = Integer.valueOf(sc.nextLine());
						if (sid <= 0 || sid > users.size())
							System.out.println("Invalid sid");
						else if (players.contains(users.get(sid - 1)))
							System.out.println("User already exists");
						else
							players.add(users.get(sid - 1));

						if (players.size() == 0)
							continue;
						System.out.println("Add more ?  1 -> Yes  2 -> No");
						int ans = Integer.valueOf(sc.nextLine());
						if (ans == 2)
							break;
					}

					List<UserEntity> finalPlayers = new ArrayList<>();
					for (UserEntity user : players)
						finalPlayers.add(user);
					String gameName = "Game";

					while (true) {
						System.out.println("Type game name");
						String name = sc.nextLine();
						if (name.equals(""))
							System.out.println("Please enter a valid name");
						else {
							gameName = name;
							break;
						}
					}

					GameType gameType = GameType.EASY;

					while (true) {
						System.out.println("Select game type   1 -> EASY  2 -> MEDIUM  3 -> HARD");
						int type = Integer.valueOf(sc.nextLine());
						if (type < 1 || type > 3)
							System.out.println("Enter a valid number");
						else {
							if (type == 1)
								gameType = GameType.EASY;
							else if (type == 2)
								gameType = GameType.MEDIUM;
							else if (type == 3)
								gameType = GameType.HARD;

							break;
						}
					}

					GameEntity newGame = gameController.createGame(gameName, finalPlayers, gameType);
					System.out.println("Game successfully created");

				} else {

				}
			}
		}
	}

}
