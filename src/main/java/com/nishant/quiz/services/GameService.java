package com.nishant.quiz.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishant.quiz.enums.GameType;
import com.nishant.quiz.factories.GameFactory;
import com.nishant.quiz.models.GameEntity;
import com.nishant.quiz.models.Leaderboard;
import com.nishant.quiz.models.PlayerPoints;
import com.nishant.quiz.models.UserEntity;
import com.nishant.quiz.repositories.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private GameFactory gameFactory;

	public GameEntity createGame(String name, GameType gameType, List<UserEntity> users) throws Exception {

		Leaderboard leaderBoard = new Leaderboard();
		List<PlayerPoints> playerPoints = new ArrayList<>();
		leaderBoard.setBoard(playerPoints);

		GameEntity newGame = gameFactory.createGame(name, gameType, users, leaderBoard);
		leaderBoard.setGame(newGame);
		gameRepository.save(newGame);
		return newGame;
	}

	public List<GameEntity> getSavedGames() {
		return gameRepository.getNewGames();
	}

	public void saveGame(GameEntity game) {
		gameRepository.save(game);

	}

}
