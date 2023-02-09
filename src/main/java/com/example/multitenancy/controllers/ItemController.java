package com.example.multitenancy.controllers;

import com.example.multitenancy.models.Item;
import com.example.multitenancy.payload.request.LoginRequest;
import com.example.multitenancy.payload.request.SignupRequest;
import com.example.multitenancy.payload.response.MessageResponse;
import com.example.multitenancy.repository.TenantRepository;
import com.example.multitenancy.repository.UserRepository;
import com.example.multitenancy.repository.UserTenantRepository;
import com.example.multitenancy.services.AuthService;
import com.example.multitenancy.services.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping()
    public ResponseEntity<List<Item>> findBySubdomain(@RequestAttribute String subdomain
    ) {
        System.out.println(subdomain);
        return ResponseEntity.ok(itemService.findBySubdomain(subdomain));
    }
}
