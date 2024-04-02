package com.nishant.quiz.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishant.quiz.enums.QuestionType;
import com.nishant.quiz.models.OptionEntity;
import com.nishant.quiz.models.QuestionEntity;
import com.nishant.quiz.repositories.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	public QuestionEntity createQuestion(String title, int questionType, int points, List<String> options, int correctOption) {

		QuestionEntity entity = new QuestionEntity();
		entity.setTitle(title);
		entity.setQuestionType(
				questionType == 1 ? QuestionType.EASY : (questionType == 2 ? QuestionType.MEDIUM : QuestionType.HARD));
		entity.setPoints(points);

		List<OptionEntity> newOptions = options.stream().map(option -> {
			OptionEntity newOption = new OptionEntity();
			newOption.setQuestion(entity);
			newOption.setOption(option);
			return newOption;
		}).collect(Collectors.toList());

		entity.setOptions(newOptions);
		entity.setAns(newOptions.get(correctOption));
		questionRepository.save(entity);
		return entity;

	}

}
