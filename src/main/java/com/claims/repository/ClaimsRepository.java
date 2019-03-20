package com.claims.repository;

import com.claims.model.Claims;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ClaimsRepository extends CrudRepository<Claims, Long> {

    @Query(value = "select * from claims order by CreatedAt desc  limit 100", nativeQuery = true)
    Collection<Claims> findLatestClaims();

    @Query(value = "select * from claims where id=?1 order by CreatedAt desc  limit 100", nativeQuery = true)
    Collection<Claims> findClaimsById(Integer id);

    @Query(value = "select * from claims where MemberID=?1 order by CreatedAt desc  limit 100", nativeQuery = true)
    Collection<Claims> findClaimsByMemberId(Integer id);

    @Query(value = "select * from claims where ProviderID=?1 order by CreatedAt desc  limit 100", nativeQuery = true)
    Collection<Claims> findClaimsByProviderId(Integer id);

    @Query(value = "select * from claims where vendor=?1 order by CreatedAt desc  limit 100", nativeQuery = true)
    Collection<Claims> findClaimsByVendorId(Integer id);

}
