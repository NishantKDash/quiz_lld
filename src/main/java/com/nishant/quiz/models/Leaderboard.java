package com.nishant.quiz.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Leaderboard {
   
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
   private Long id;
	
	@OneToOne
	private GameEntity game;
	
	@OneToMany(mappedBy="leaderBoard", cascade = CascadeType.ALL)
	private List<PlayerPoints> board;
}
