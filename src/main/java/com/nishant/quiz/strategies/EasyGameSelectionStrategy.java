package com.nishant.quiz.strategies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nishant.quiz.models.QuestionEntity;
import com.nishant.quiz.repositories.QuestionRepository;

@Component
public class EasyGameSelectionStrategy implements GameSelectionStrategy {

	private QuestionRepository questionRepository;

	public EasyGameSelectionStrategy(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	@Override
	public List<QuestionEntity> selectQuestions() {
		List<QuestionEntity> questions = new ArrayList<>();
		List<QuestionEntity> easyQuestions = questionRepository.getEasyQuestions();
		List<QuestionEntity> mediumQuestions = questionRepository.getMediumQuestions();
		List<QuestionEntity> hardQuestions = questionRepository.getHardQuestions();

		int eq = 0;
		int mq = 0;
		int hq = 0;

		while (eq < 5) {
			int index = (int) Math.random() * easyQuestions.size();
			questions.add(easyQuestions.get(index));
			easyQuestions.remove(index);
			eq++;
		}
		while (mq < 3) {
			int index = (int) Math.random() * mediumQuestions.size();
			questions.add(mediumQuestions.get(index));
			mediumQuestions.remove(index);
			mq++;
		}
		while (hq < 1) {
			int index = (int) Math.random() * hardQuestions.size();
			questions.add(hardQuestions.get(index));
			hardQuestions.remove(index);
			hq++;
		}

		return questions;
	}

}
