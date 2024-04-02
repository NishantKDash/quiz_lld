package com.nishant.quiz.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nishant.quiz.enums.GameState;
import com.nishant.quiz.enums.GameType;
import com.nishant.quiz.models.GameEntity;
import com.nishant.quiz.models.Leaderboard;
import com.nishant.quiz.models.OptionEntity;
import com.nishant.quiz.models.PlayerPoints;
import com.nishant.quiz.models.QuestionEntity;
import com.nishant.quiz.models.UserEntity;
import com.nishant.quiz.services.GameService;

@Controller
public class GameController {

	@Autowired
	private GameService gameService;

	public GameEntity createGame(String name, List<UserEntity> players, GameType gameType) {
		try {
			GameEntity newGame = gameService.createGame(name, gameType, players);
			return newGame;
		} catch (Exception e) {
			System.out.println("Unable to create a game");
			e.printStackTrace();
			return null;
		}
	}

	public void playGame(GameEntity game) throws InterruptedException {
		System.out.println("Game loading...");
		Thread.sleep(5000);

		System.out.println("Game started !");
		Thread.sleep(1000);

		List<UserEntity> participants = game.getPlayers();
		int turn = game.getTurn();
		List<QuestionEntity> questions = game.getQuestions();

		HashMap<String, Integer> rankings = new HashMap<>();

		Leaderboard leaderBoard = game.getLeaderboard();

		boolean pauseFlag = false;
		while (game.getGameState().equals(GameState.NEW) || !pauseFlag) {
			if (turn == questions.size()) {
				game.setGameState(GameState.COMPLETE);
				gameService.saveGame(game);
				break;
			}
			Scanner sc = new Scanner(System.in);
			int pturn = turn % participants.size();
			UserEntity participant = participants.get(pturn);
			Thread.sleep(1000);

			System.out.println("Question for participant -> " + participant.getName());
			QuestionEntity question = questions.get(turn++);
			List<OptionEntity> options = question.getOptions();
			System.out.println(question.getTitle());
			System.out.println("Your options are -> ");
			for (int i = 0; i < options.size(); i++)
				System.out.println((i + 1) + " -> " + options.get(i).getOption());
			System.out.println("* FOR Leaderboard   # FOR Pause");

			int option = 1;
			while (true) {
				System.out.println("Enter answer ");
				String p = sc.nextLine();
				if (p.equals("#") || p.equals("*")) {
					if (p.equals("*")) {
						for (int i = 0; i < leaderBoard.getBoard().size(); i++) {
							PlayerPoints playerPoint = leaderBoard.getBoard().get(i);
							rankings.put(playerPoint.getPlayer().getName(),
									rankings.getOrDefault(playerPoint.getPlayer().getName(), 0)
											+ playerPoint.getPoint());
						}

						for (String player : rankings.keySet()) {
							System.out.println(player + " -> " + rankings.get(player));
						}
					} else {
						pauseFlag = true;
						turn--;
						game.setTurn(turn);
						gameService.saveGame(game);
						break;
					}
				} else {
					int ans = Integer.valueOf(p);
					if (ans < 1 || ans > options.size())
						System.out.println("Enter a valid option");
					else {
						option = ans;
						break;
					}
				}

			}
			

			if (pauseFlag)
				break;

			if (options.get(option-1).getOption().equals(question.getAns().getOption())) {
				System.out.println("Correct Ans !");
				System.out.println(question.getPoints() + " awarded to " + participant.getName());
				PlayerPoints playerPoint = new PlayerPoints();
				playerPoint.setLeaderBoard(leaderBoard);
				leaderBoard.getBoard().add(playerPoint);
				playerPoint.setPlayer(participant);
				playerPoint.setPoint(question.getPoints());
				game.setTurn(turn);
				gameService.saveGame(game);

			} else {
				System.out.println("Wrong Ans !");
				PlayerPoints playerPoint = new PlayerPoints();
				playerPoint.setLeaderBoard(leaderBoard);
				leaderBoard.getBoard().add(playerPoint);
				playerPoint.setPlayer(participant);
				playerPoint.setPoint(0);
				game.setTurn(turn);
				gameService.saveGame(game);

			}

		}

		if (pauseFlag)
			System.out.println("Game paused and exited");
		else if (game.getGameState().equals(GameState.COMPLETE)) {
			System.out.println("Game ended");
			System.out.println("---- RANKINGS ----");
			Thread.sleep(1000);
			for (int i = 0; i < leaderBoard.getBoard().size(); i++) {
				PlayerPoints playerPoint = leaderBoard.getBoard().get(i);
				rankings.put(playerPoint.getPlayer().getName(),
						rankings.getOrDefault(playerPoint.getPlayer().getName(), 0) + playerPoint.getPoint());
			}

			for (String player : rankings.keySet()) {
				System.out.println(player + " -> " + rankings.get(player));
			}
		}

	}

	public List<GameEntity> getSavedGames() {
		return gameService.getSavedGames();
	}

}
