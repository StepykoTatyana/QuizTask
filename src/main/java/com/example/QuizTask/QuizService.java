package com.example.QuizTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    Quiz quiz = new Quiz();

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
}
