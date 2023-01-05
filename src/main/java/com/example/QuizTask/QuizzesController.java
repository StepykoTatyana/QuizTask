package com.example.QuizTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Component
public class QuizzesController {

    @Autowired
    QuizzesService quizzesService;

    @Autowired
    QuizzesRepository quizzesRepository;

//    @GetMapping("/api/quizzes")
//    public ResponseEntity<?> getAnswer() {
//        return quizzesService.getQuestion();
//    }

//    @PostMapping("/api/quizzes")
//    public  ResponseEntity<?> postAnswer(@RequestParam() String answer) {
//        return quizzesService.saveAnswer(answer);
//    }

    @PostMapping("/api/quizzes")
    public  ResponseEntity<?> createQuestionUser(@Validated @RequestBody() Quizzes quizzes) {
        return quizzesService.createQuestion(quizzes);
    }

    @GetMapping("/api/quizzes")
    public  ResponseEntity<?> getQuestion() {
        return quizzesService.getQuestions();
    }

    @GetMapping("/api/quizzes/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return quizzesService.getQuestionById(id);
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public  ResponseEntity<?> postAnswerWithId(@PathVariable long id, @RequestBody() Answer answer) {
        return quizzesService.getSolveWithId(id, answer.getAnswer());
    }
}
