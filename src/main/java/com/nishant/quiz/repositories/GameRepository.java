package com.nishant.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nishant.quiz.models.GameEntity;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

}
