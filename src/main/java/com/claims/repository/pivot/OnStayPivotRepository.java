package com.claims.repository.pivot;

import com.claims.model.OnStayPivot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OnStayPivotRepository extends CrudRepository<OnStayPivot, Long> {

    Collection<OnStayPivot> findAll();
}
