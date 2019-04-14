package com.claims.repository.pivot;

import com.claims.model.OnCharlsonPivot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OnCharlsonPivotRepository extends CrudRepository<OnCharlsonPivot, Long> {

    Collection<OnCharlsonPivot> findAll();
}
