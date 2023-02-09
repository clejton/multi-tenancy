package com.example.multitenancy.repository;

import com.example.multitenancy.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByTenant_Subdomain(String subdomain);

}
