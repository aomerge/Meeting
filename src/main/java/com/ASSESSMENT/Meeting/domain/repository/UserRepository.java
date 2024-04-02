package com.ASSESSMENT.Meeting.domain.repository;
import com.ASSESSMENT.Meeting.config.StructureToken.UserToken;
import com.ASSESSMENT.Meeting.persistence.entity.User;
import java.util.List;
import java.util.Optional;
public interface UserRepository {
    String getTest();
    List<User> getAll();
    Optional<User> getUser(Long id);
    Optional<User> getByUsername(String username);
    User update(String token, User user);
    User save(User user);
    void delete(String token, String confirm );
    UserToken login(User user);
    User tokenAuth(String token);
}
