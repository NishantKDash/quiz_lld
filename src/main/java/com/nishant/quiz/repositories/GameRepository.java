package com.nishant.quiz.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nishant.quiz.models.GameEntity;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

	@Query("SELECT g FROM GameEntity g WHERE g.gameState = 'NEW'")
	public List<GameEntity> getNewGames();
}
