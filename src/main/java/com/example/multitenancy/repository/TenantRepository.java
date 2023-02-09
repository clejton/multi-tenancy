package com.example.multitenancy.repository;

import com.example.multitenancy.models.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {

    Boolean existsBySubdomain(String subdomain);


}
