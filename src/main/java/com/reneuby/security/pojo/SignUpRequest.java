package com.reneuby.security.pojo;

import lombok.Data;

import java.util.Set;

@Data
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
    private Set<String> roles;
}
