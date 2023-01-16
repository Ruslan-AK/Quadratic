package com.reneuby.security.repository;

import com.reneuby.security.model.Role;
import com.reneuby.security.model.enums.RolesEnum;

public interface UserRoleDao {
    Role findByName(RolesEnum name);
}
