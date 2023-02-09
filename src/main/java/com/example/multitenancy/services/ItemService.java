package com.example.multitenancy.services;

import com.example.multitenancy.models.Item;
import com.example.multitenancy.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> findBySubdomain(String subdomain) {
        return itemRepository.findByTenant_Subdomain(subdomain);
    }


}
