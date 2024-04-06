package com.ASSESSMENT.Meeting.persistence.crud;

import com.ASSESSMENT.Meeting.persistence.entity.Attendees;
import com.ASSESSMENT.Meeting.persistence.entity.Session;
import com.ASSESSMENT.Meeting.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AttendesCrudRepository extends JpaRepository<Attendees, Long> {
    @Query(value = "SELECT * FROM attendees WHERE session_id = :sessionId AND user_id = :userId", nativeQuery = true)
    Attendees findBySessionIdAndUserId(@Param("sessionId") Long sessionId, @Param("userId") Long userId);

}
