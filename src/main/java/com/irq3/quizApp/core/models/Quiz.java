package com.irq3.quizApp.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.irq3.quizApp.core.Converters.QuestionsConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "quiz")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    @Column(name = "hiddenname")
    String hiddenName;
    String description;
    @Convert(converter = QuestionsConverter.class)
    List<Question> content;
    private long user_id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", insertable = false)
    LocalDateTime createdAt;
    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    public void changMe(Quiz quiz){
        this.name = quiz.name;
        this.hiddenName = quiz.hiddenName;
        this.content = quiz.content;
        this.updatedAt = LocalDateTime.now();
    }
}
