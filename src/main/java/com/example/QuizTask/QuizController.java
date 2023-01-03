package com.example.QuizTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping("/api/quiz")
    public ResponseEntity<?> getAnswer() {
        return quizService.getQuestion();
    }

    @PostMapping("/api/quiz")
    public  ResponseEntity<?> postRecipes(@RequestParam() String answer) {
        return quizService.saveAnswer(answer);
    }
}
