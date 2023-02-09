package com.example.multitenancy.repository;

import com.example.multitenancy.models.UserTenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTenantRepository extends JpaRepository<UserTenant, Long> {

    @Query("SELECT ut FROM UserTenant ut WHERE ut.user.username = :username AND ut.tenant.subdomain = :subdomain ")
    Optional<UserTenant> findByUserAndSubdomain(String username, String subdomain);

    @Query("SELECT " +
            "CASE WHEN COUNT(ut) > 0 THEN true ELSE false END " +
            "FROM UserTenant ut " +
            "WHERE ut.user.username = :username " +
            "AND ut.tenant.subdomain = :subdomain ")
    Boolean existsByUsernameAndSubdomain(String subdomain, String username);

}
