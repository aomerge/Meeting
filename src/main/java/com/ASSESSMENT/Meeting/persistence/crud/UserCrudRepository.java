package com.ASSESSMENT.Meeting.persistence.crud;

import com.ASSESSMENT.Meeting.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Interface that extends JpaRepository to manage the User entity
 */
public interface UserCrudRepository extends JpaRepository<User, Long> {
    List<User> findByEmailAndPassword(String email, String password);
    User findByEmail(String email);


}
