package com.nishant.quiz.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nishant.quiz.models.QuestionEntity;
import com.nishant.quiz.services.QuestionService;

@Controller
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	public void createQuestion() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter question type " + " EASY - 1" + "MEDIUM - 2" + "HARD - 3");
		int type = Integer.valueOf(sc.nextLine());
		System.out.println("Enter the question title");
		String title = sc.nextLine();

		System.out.println("Enter the points the question carries ");
		int points = Integer.valueOf(sc.nextLine());
		System.out.println("Enter number of options");
		int options = Integer.valueOf(sc.nextLine());
		int correctOptionidx = -1;
		List<String> o = new ArrayList<>();
		while (options-- > 0) {
			System.out.println("Please enter option:" + (options + 1));
			o.add(sc.nextLine());
			if (correctOptionidx == -1) {
				int option = 1;
				while (true) {
					System.out.println("Is this the answer ?  1 -> Yes  2  -> No");
					option = Integer.valueOf(sc.nextLine());
					if (option < 1 || option > 2)
						System.out.println("Please enter a valid option");
					else
						break;
				}

				if (option == 1)
					correctOptionidx = o.size() - 1;

			}
		}

		while (correctOptionidx == -1) {
			System.out.println("You did not specify the correct answer !");
			int ans = Integer.valueOf(sc.nextLine());
			if (ans <= 0 || ans > o.size())
				continue;
			else {
				correctOptionidx = ans;
				break;
			}
		}

		try {
			QuestionEntity question = questionService.createQuestion(title, type, points, o, correctOptionidx);
			System.out.println("Question created successfully with id: " + question.getId());
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Some error occurred");
		}
	}
}
