package com.nishant.quiz.services;

import java.util.List;
import java.util.stream.Collectors;

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
	

	public GameEntity createGame(String name, GameType gameType, List<UserEntity> users) {

		Leaderboard leaderBoard = new Leaderboard();
		List<PlayerPoints> playerPoints = users.stream().map(user -> {
			PlayerPoints playerPoint = new PlayerPoints();
			playerPoint.setLeaderBoard(leaderBoard);
			playerPoint.setPlayer(user);
			playerPoint.setPoint(0);
			return playerPoint;
		}).collect(Collectors.toList());
		leaderBoard.setBoard(playerPoints);

		GameEntity newGame = gameFactory.createGame(name, gameType, users, leaderBoard);
		leaderBoard.setGame(newGame);
		gameRepository.save(newGame);
		return newGame;
	}

}
