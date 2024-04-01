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
		HashSet<Integer> easyIndices = indices(5, easyQuestions.size());

		for (int ele : easyIndices) {
			questions.add(easyQuestions.get(ele));
		}

		for (int i = 0; i < mediumQuestions.size(); i++) {
			int rand = (int) Math.floor(Math.random() * 10);
			if (rand % 2 == 0) {
				questions.add(mediumQuestions.get(i));

			}
		}

		for (int i = 0; i < hardQuestions.size(); i++) {
			int rand = (int) Math.floor(Math.random() * 10);
			if (rand % 2 == 0) {
				questions.add(hardQuestions.get(i));
			}
		}

		return questions;
	}

	public HashSet<Integer> indices(int n, int size) {

		HashSet<Integer> set = new HashSet<>();
		if (n >= size) {
			while (size-- > 0)
				set.add(size);
			return set;
		}
		while (set.size() == n) {
			int index = (int) Math.random() * size;
			if (!set.contains(index))
				set.add(index);
		}

		return set;

	}

}
