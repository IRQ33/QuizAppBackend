package com.irq3.quizApp.auth.repositories;

import com.irq3.quizApp.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User getUserByEmail(String email);
    boolean existsByEmail(String email);
    User getUserByUserName(String userName);
    boolean existsByUserName(String userName);
    User getUserById(long id);
    boolean existsById(long id);


}
