package com.ASSESSMENT.Meeting.persistence.crud;

import com.ASSESSMENT.Meeting.persistence.entity.Attendees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AttendesCrudRepository extends JpaRepository<Attendees, Long> {
}
