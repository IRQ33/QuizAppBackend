package com.irq3.quizApp.core.repositories;

import com.irq3.quizApp.core.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
    Quiz getQuizById(long id);
    @Query(nativeQuery = true, value = "SELECT * FROM quiz e ORDER BY similarity(LOWER(e.hiddenName), LOWER(:name)) DESC LIMIT 10")
    List<Quiz> getSimilarQuizzes(@Param("name") String name);
}

