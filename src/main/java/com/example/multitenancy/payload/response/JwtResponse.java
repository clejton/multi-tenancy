package com.example.multitenancy.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private Long id;
    private String username;
    private List<String> roles;
    private String tokenType = "Bearer";
    private String accessToken;

    public JwtResponse(String accessToken, Long id, String username,
                       List<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.accessToken = accessToken;
    }

}
