package com.restful.spring.boot.restful_spring_boot.repository;

import com.restful.spring.boot.restful_spring_boot.model.UserTokenSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenSessionRepository extends CrudRepository<UserTokenSession, Long> {

    UserTokenSession findOneByUsername(String username);
}
