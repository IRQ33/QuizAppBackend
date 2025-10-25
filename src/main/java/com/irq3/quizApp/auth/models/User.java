package com.irq3.quizApp.auth.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.irq3.quizApp.auth.converters.PermissionConverter;
import com.irq3.quizApp.auth.enums.Permissions;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_name")
    private String userName;
    @JsonIgnore
    @Size(min = 8)
    private String password;
    @JsonIgnore
    @Email
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Convert(converter = PermissionConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<Permissions> permissions;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", insertable = false)
    Date createdAt;
    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    Date updatedAt;


}
