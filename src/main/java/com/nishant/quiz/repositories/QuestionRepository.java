package com.nishant.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nishant.quiz.models.QuestionEntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity,Long>{

}
