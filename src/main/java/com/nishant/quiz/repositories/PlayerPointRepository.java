package com.nishant.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nishant.quiz.models.PlayerPoints;

@Repository
public interface PlayerPointRepository extends JpaRepository<PlayerPoints, Long> {

}
