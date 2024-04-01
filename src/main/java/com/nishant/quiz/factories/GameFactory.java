package com.nishant.quiz.factories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishant.quiz.enums.GameType;
import com.nishant.quiz.models.GameEntity;
import com.nishant.quiz.models.GameEntity.GameBuilder;
import com.nishant.quiz.models.Leaderboard;
import com.nishant.quiz.models.QuestionEntity;
import com.nishant.quiz.models.UserEntity;
import com.nishant.quiz.repositories.QuestionRepository;
import com.nishant.quiz.strategies.EasyGameSelectionStrategy;
import com.nishant.quiz.strategies.GameSelectionStrategy;

@Service
public class GameFactory {

	@Autowired
	private QuestionRepository questionRepository;

	public GameEntity createGame(String name, GameType gameType, List<UserEntity> players, Leaderboard leaderBoard) {
		GameBuilder gameBuilder = GameEntity.getGameBuilder();
		if (gameType.equals(GameType.EASY)) {
			GameSelectionStrategy gameSelectionStrategy = new EasyGameSelectionStrategy(questionRepository);
			List<QuestionEntity> questions = gameSelectionStrategy.selectQuestions();
			return gameBuilder.setName(name).setPlayers(players).setQuestions(questions).setLeaderBoard(leaderBoard)
					.build();
		} else
			return null;
	}
}
