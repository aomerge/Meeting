package com.ASSESSMENT.Meeting.persistence.crud;

import com.ASSESSMENT.Meeting.persistence.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SessionCrudRepository extends JpaRepository<Session, Long> {
}
