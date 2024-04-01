package com.nishant.quiz.strategies;

import java.util.List;

import com.nishant.quiz.models.QuestionEntity;

public interface GameSelectionStrategy {
	public List<QuestionEntity> selectQuestions();
}
