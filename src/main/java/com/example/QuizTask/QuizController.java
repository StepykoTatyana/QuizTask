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
    public  ResponseEntity<?> postAnswer(@RequestParam() String answer) {
        return quizService.saveAnswer(answer);
    }

    @PostMapping("/api/quizzes")
    public  ResponseEntity<?> createQuestionUser(@RequestBody() Quiz quiz) {
        return quizService.createQuestion(quiz);
    }

    @GetMapping("/api/quizzes")
    public  ResponseEntity<?> getQuestion() {
        return quizService.getQuestions();
    }

    @GetMapping("/api/quiz/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return quizService.getQuestionById(id);
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public  ResponseEntity<?> postAnswerWithId(@PathVariable long id, @RequestParam() int answer) {
        return quizService.getSolveWithId(id, answer);
    }
}
