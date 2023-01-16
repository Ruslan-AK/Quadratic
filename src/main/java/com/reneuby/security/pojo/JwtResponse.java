package com.reneuby.security.pojo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class JwtResponse {
    private final Long userId;
    private final String token;
    private final String userName;
    private final String userEmail;
    private final List<String> roles;
    private String type = "Bearer";
}
