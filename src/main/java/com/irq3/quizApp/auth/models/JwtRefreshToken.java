package com.irq3.quizApp.auth.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "jwt_refresh_tokens")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class JwtRefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private long user_id;
    private String token;
    @Column(name = "dateexpired")
    private Date dateExpired;
    @Column(name = "datecreated")
    private Date dateCreated;
}
