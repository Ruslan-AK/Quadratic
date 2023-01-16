package com.reneuby.security.repository.impl;

import com.reneuby.security.model.Role;
import com.reneuby.security.model.enums.RolesEnum;
import com.reneuby.security.repository.UserRoleDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserRoleDaoImpl implements UserRoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findByName(RolesEnum name) {
        return entityManager.createQuery("From Role r where r.role = :role", Role.class)
                .setParameter("role", name)
                .getSingleResult();
    }
}
