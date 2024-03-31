package com.nishant.quiz.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

	@Column(nullable=false)
	private String name;

	private LocalDateTime created;

	@ManyToMany
	@JoinTable(name = "game_participants", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "player_id"))
	private List<UserEntity> players;

	@ManyToMany
	@JoinTable(name = "game_questions" , joinColumns= @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
	private List<QuestionEntity> questions;

	@OneToOne
	private Leaderboard leaderboard;

	@ManyToOne
	private UserEntity winner;

	private int turn;
}
