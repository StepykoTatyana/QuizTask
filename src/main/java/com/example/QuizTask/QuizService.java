package com.example.QuizTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    Quiz quiz = new Quiz();
    long id = 1;
    List<Quiz> list = new ArrayList<>();

    public ResponseEntity<?> saveAnswer(String answer) {
        Feedback feedback = new Feedback();
        if (Integer.parseInt(answer) == 2) {
            feedback.setSuccess(true);
            feedback.setFeedback("Congratulations, you're right!");

        } else {
            feedback.setSuccess(false);
            feedback.setFeedback("Wrong answer! Please, try again.");
        }
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }

    public ResponseEntity<?> getQuestion() {

        quiz.setTitle("The Java Logo");
        quiz.setText("What is depicted on the Java logo?");
        quiz.setOptions(List.of(new String[]{"Robot", "Tea leaf", "Cup of coffee", "Bug"}));
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    public ResponseEntity<?> createQuestion(Quiz quiz) {
        quiz.setId(id);
        System.out.println(quiz.getAnswer());
        id++;
        list.add(quiz);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    public ResponseEntity<?> getQuestions() {
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<?> getQuestionById(long idUser) {
        for (Quiz quiz : list) {
            if (quiz.getId() == idUser) {
                return new ResponseEntity<>(quiz, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getSolveWithId(long idUser, int answer) {
        Feedback feedback = new Feedback();
        for (Quiz quiz : list) {
            if (quiz.getId() == idUser) {
                if (quiz.getAnswer() == answer) {
                    feedback.setSuccess(true);
                    feedback.setFeedback("Congratulations, you're right!");
                } else {
                    feedback.setSuccess(false);
                    feedback.setFeedback("Wrong answer! Please, try again.");
                }
                return new ResponseEntity<>(feedback, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
