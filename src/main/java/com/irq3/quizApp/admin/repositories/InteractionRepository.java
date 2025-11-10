package com.irq3.quizApp.admin.repositories;

import com.irq3.quizApp.admin.models.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteractionRepository extends JpaRepository<Interaction,Long> {
    Interaction getInteractionById(long id);
}
