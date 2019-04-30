package com.claims.repository;

import com.claims.model.UserLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface AudiRepository extends CrudRepository<UserLog, Long> {

    @Query(value = "select * from user_log order by createdAt desc  limit 100", nativeQuery = true)
    Collection<UserLog> getLogs();
}
