package com.example.QuizTask;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuizzesRepository extends
        PagingAndSortingRepository<Quizzes, Long> {
    @Query(value = "Select*from quizzes", nativeQuery = true)
    List<Quizzes> findAllQuizzes();

    @Query(value = "Select top 1 id from quizzes order by id desc", nativeQuery = true)
    long lastId();
}
