package com.nishant.quiz.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nishant.quiz.models.QuestionEntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

	@Query("SELECT q FROM QuestionEntity q WHERE q.questionType = 'EASY'")
	public List<QuestionEntity> getEasyQuestions();

	@Query("SELECT q FROM QuestionEntity q WHERE q.questionType = 'MEDIUM'")
	public List<QuestionEntity> getMediumQuestions();

	@Query("SELECT q FROM QuestionEntity q WHERE q.questionType = 'HARD'")
	public List<QuestionEntity> getHardQuestions();
}
