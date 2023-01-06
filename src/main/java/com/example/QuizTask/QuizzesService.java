package com.example.QuizTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class QuizzesService {

    @Autowired
    QuizzesRepository repository;

    @Autowired
    QuizzesRepositoryWithCrud repositoryWithCrud;

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

    public ResponseEntity<?> createQuestion(Quizzes quizzes, String email) {
        repositoryWithCrud.save(new Quizzes(quizzes.getTitle(), quizzes.getText(),
                quizzes.getOptions(), quizzes.getAnswer(), email));
        quizzes.setId(repository.lastId());
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    public ResponseEntity<?> getQuestions() {
        return new ResponseEntity<>(repository.findAllQuizzes(), HttpStatus.OK);
    }

    public ResponseEntity<?> getQuestionById(long idUser) {
        try {
            Quizzes quiz = repositoryWithCrud.findById(idUser).get();
            return new ResponseEntity<>(quiz, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getSolveWithId(long idUser, List<Integer> answer, UserDetails details) {
        Feedback feedback = new Feedback();
        try {
            Quizzes quiz = repositoryWithCrud.findById(idUser).get();
            System.out.println(idUser);
            System.out.println(details.getUsername());
            System.out.println(repository.lastId());
            if ((quiz.getAnswer() == null && (answer == null || answer.size() == 0))
                    || Arrays.toString(answer.toArray()).equals(Arrays.toString(quiz.getAnswer().toArray()))) {
                feedback.setSuccess(true);
                feedback.setFeedback("Congratulations, you're right!");
            } else {
                feedback.setSuccess(false);
                feedback.setFeedback("Wrong answer! Please, try again.");
            }
            return new ResponseEntity<>(feedback, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteById(long id) {
        repositoryWithCrud.deleteById(id);
    }


    public ResponseEntity<?> getQuestionsWithPage(Integer pageNo, Integer pageSize) {
        Page<Quizzes> pagedResult = repository.findAll(PageRequest.of(pageNo, pageSize, Sort.by("id").descending()));

        if(pagedResult.hasContent()) {
            return new ResponseEntity<>(pagedResult.getContent(), HttpStatus.OK) ;
        } else {
            return new ResponseEntity<>(new ArrayList<Quizzes>(), HttpStatus.OK);
        }

    }


    public ResponseEntity<?> getCompletedAnswers() {
        return new ResponseEntity<>(new ArrayList<Quizzes>(), HttpStatus.OK);
    }
}
