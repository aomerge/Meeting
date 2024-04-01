package com.ASSESSMENT.Meeting.persistence.crud;

import org.hibernate.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SessionCrudRepository extends CrudRepository<Session, Long>{
    List<Session> findAllByOrderBySessionScheduleAsc();
}
