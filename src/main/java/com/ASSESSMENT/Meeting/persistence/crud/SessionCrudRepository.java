package com.ASSESSMENT.Meeting.persistence.crud;

import com.ASSESSMENT.Meeting.persistence.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SessionCrudRepository extends JpaRepository<Session, Long> {
    @Query(value = "SELECT * FROM session WHERE access = :accesType", nativeQuery = true)
    List<Session> findAllByAccessType(String accesType);

    @Query(value = "SELECT * FROM session WHERE session_id = :sessionId AND access = :accesType", nativeQuery = true)
    Session findBySessionIdAndAccessType(Long sessionId, String accesType);


}
