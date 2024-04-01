package com.nishant.quiz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nishant.quiz.enums.GameType;
import com.nishant.quiz.models.GameEntity;
import com.nishant.quiz.models.UserEntity;
import com.nishant.quiz.services.GameService;

@Controller
public class GameController {

	@Autowired
	private GameService gameService;

	public GameEntity createGame(String name, List<UserEntity> players, GameType gameType) {
		return gameService.createGame(name, gameType, players);
	}
}
