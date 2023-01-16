package com.reneuby.security.repository;

import com.reneuby.security.model.User;

public interface UserDao {
    User findUserByName(String name);
    boolean existsByName(String name);
    boolean existsByEmail(String email);
    void deleteUserByName(String name);
    User saveUser(User user);
}
