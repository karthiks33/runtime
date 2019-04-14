package com.claims.repository.pivot;

import com.claims.model.OnConditionPivot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OnConditionPivotRepository extends CrudRepository<OnConditionPivot, Long> {

    Collection<OnConditionPivot> findAll();
}
