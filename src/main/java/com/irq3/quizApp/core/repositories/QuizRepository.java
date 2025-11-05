package com.irq3.quizApp.core.repositories;

import com.irq3.quizApp.core.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
    Quiz getQuizById(long id);
}
