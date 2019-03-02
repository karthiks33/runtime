package com.claims.repository;

import com.claims.model.Claims;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimsRepository extends CrudRepository<Claims,Long> {

}
