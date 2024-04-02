package com.nishant.quiz.models;

import java.time.LocalDateTime;
import java.util.List;

import com.nishant.quiz.enums.GameState;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false)
	private String name;

	private LocalDateTime created;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "game_participants", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "player_id"))
	private List<UserEntity> players;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "game_questions", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
	private List<QuestionEntity> questions;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Leaderboard leaderboard;

	@ManyToOne
	private UserEntity winner;

	private int turn;

	@Enumerated(EnumType.STRING)
	private GameState gameState;

	public static GameBuilder getGameBuilder() {
		return new GameBuilder();
	}

	public static class GameBuilder {
		private String name;
		private LocalDateTime created = LocalDateTime.now();
		private List<UserEntity> players;
		private List<QuestionEntity> questions;
		private Leaderboard leaderBoard;
		private UserEntity winner = null;
		private int turn = 0;

		public GameBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public GameBuilder setPlayers(List<UserEntity> players) {
			this.players = players;
			return this;
		}

		public GameBuilder setQuestions(List<QuestionEntity> questions) throws Exception {

			this.questions = questions;

			return this;
		}

		public GameBuilder setLeaderBoard(Leaderboard leaderBoard) {
			this.leaderBoard = leaderBoard;
			return this;
		}

		public GameEntity build() {
			GameEntity newGame = new GameEntity();
			newGame.setCreated(created);
			newGame.setLeaderboard(leaderBoard);
			newGame.setName(name);
			newGame.setPlayers(players);
			newGame.setQuestions(questions);
			newGame.setTurn(turn);
			newGame.setWinner(winner);
			newGame.setGameState(GameState.NEW);
			return newGame;
		}

	}
}
