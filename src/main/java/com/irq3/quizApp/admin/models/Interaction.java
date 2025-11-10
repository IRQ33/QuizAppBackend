package com.irq3.quizApp.admin.models;

import com.irq3.quizApp.admin.converters.InteractionTypeConverter;
import com.irq3.quizApp.admin.enums.InteractionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "interactions")
@NoArgsConstructor @AllArgsConstructor @Data @Builder
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Convert(converter = InteractionTypeConverter.class)
    @Column(name = "interaction_type")
    InteractionType interactionType;
    @Column(name = "admin_id")
    long adminId;
    @Column(name = "user_id")
    long userId;
    String reason;
    @Column(name = "interaction_date")
    LocalDateTime interactionDate;

}
