package com.reneuby.controllers;

import com.reneuby.security.config.jwt.JwtUtils;
import com.reneuby.security.model.Role;
import com.reneuby.security.model.User;
import com.reneuby.security.model.enums.RolesEnum;
import com.reneuby.security.pojo.JwtResponse;
import com.reneuby.security.pojo.LoginRequest;
import com.reneuby.security.pojo.Response;
import com.reneuby.security.pojo.SignUpRequest;
import com.reneuby.security.repository.UserDao;
import com.reneuby.security.repository.UserRoleDao;
import com.reneuby.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRoleDao userRoleDao;
    private final UserDao userDao;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getName(),
                        loginRequest.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtWebToken = jwtUtils.generateJwtWebToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(
                userDetails.getId(),
                jwtWebToken,
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest request) {
        if (userDao.existsByName(request.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new Response("User with name " + request.getName() + " already exists"));
        }
        if (userDao.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new Response("User with email " + request.getEmail() + " already exists"));
        }
        try {
            Set<Role> roles = request.getRoles().stream()
                    .map(roleString -> this.resolveRole(roleString))
                    .collect(Collectors.toSet());
            User user = new User(
                    null,
                    request.getName(),
                    request.getEmail(),
                    passwordEncoder.encode(request.getPassword()),
                    roles);
            userDao.saveUser(user);
        } catch (IllegalArgumentException e) {
            log.info("Can't parse role: " + e);
            return ResponseEntity.badRequest().body("User not created");
        }
        return ResponseEntity.ok(new Response("User created"));
    }

    private Role resolveRole(String roleString) {
        switch (roleString) {
            case "user":
                return new Role(null, RolesEnum.ROLE_USER);
            case "admin":
                return new Role(null, RolesEnum.ROLE_ADMIN);
            default:
                throw new RuntimeException("Unknown role: " + roleString);
        }
    }

    @GetMapping("/test")
    public void printStateToLog() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String username = authentication.getName();
        Object principal = authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        log.info("!Authorities: " + authorities);
    }
}
