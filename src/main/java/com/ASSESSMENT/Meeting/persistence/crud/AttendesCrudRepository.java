package com.ASSESSMENT.Meeting.persistence.crud;

import com.ASSESSMENT.Meeting.persistence.entity.Attendees;
import org.springframework.data.repository.CrudRepository;

public interface AttendesCrudRepository extends CrudRepository<Attendees, Long> {
}
