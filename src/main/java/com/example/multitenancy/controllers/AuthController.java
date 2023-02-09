package com.example.multitenancy.controllers;

import com.example.multitenancy.payload.request.LoginRequest;
import com.example.multitenancy.payload.request.SignupRequest;
import com.example.multitenancy.payload.response.MessageResponse;
import com.example.multitenancy.repository.TenantRepository;
import com.example.multitenancy.repository.UserRepository;
import com.example.multitenancy.repository.UserTenantRepository;
import com.example.multitenancy.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final UserTenantRepository userTenantRepository;
    private final TenantRepository tenantRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(
            @RequestAttribute String subdomain,
            @Valid @RequestBody LoginRequest loginRequest
    ) {
        if (!tenantRepository.existsBySubdomain(subdomain)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Subdomain is not found!"));
        }
        if (!userTenantRepository.existsByUsernameAndSubdomain(subdomain, loginRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Unauthorized: The user is not authorized for this tenant!"));
        }
        return ResponseEntity.ok(authService.authenticateUser(subdomain, loginRequest));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        return ResponseEntity.ok(authService.registerUser(signUpRequest));
    }
}
