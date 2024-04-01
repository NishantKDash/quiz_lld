package com.nishant.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nishant.quiz.models.Leaderboard;

@Repository
public interface  LeaderboardRepository extends JpaRepository<Leaderboard,Long>{

}
