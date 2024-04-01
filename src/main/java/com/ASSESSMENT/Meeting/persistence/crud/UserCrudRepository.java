package com.ASSESSMENT.Meeting.persistence.crud;

import com.ASSESSMENT.Meeting.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepository extends CrudRepository<User, Long> {

}
