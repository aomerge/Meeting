package com.ASSESSMENT.Meeting.domain.repository;

import com.ASSESSMENT.Meeting.persistence.entity.Session;

import java.util.List;

public interface SessionRepository {
    List<Session> getAll();
    Session save(String token, Session session);
    void delete(String token, String confirm );
    Session update(String Token,  Session session);

}
