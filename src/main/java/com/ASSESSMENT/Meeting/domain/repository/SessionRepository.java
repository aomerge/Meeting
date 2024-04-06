package com.ASSESSMENT.Meeting.domain.repository;

import com.ASSESSMENT.Meeting.config.StructureToken.SessionDto;
import com.ASSESSMENT.Meeting.persistence.entity.Attendees;
import com.ASSESSMENT.Meeting.persistence.entity.Session;
import com.ASSESSMENT.Meeting.persistence.entity.User;

import java.util.List;

public interface SessionRepository {
    List<SessionDto> getAll();
    Session save(String token, Session session);
    void delete(String token, Long id,  String confirm );
    Session update(String token, Long id ,Session session);
    User register(String token, Long id);
    User deleteRegister(String token, Long id);
    Session getOne(Long id);
    User registerPrivate(String token, Long id, String code,  String email);
}
