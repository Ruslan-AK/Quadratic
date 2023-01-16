package com.reneuby.security.repository.impl;

import com.reneuby.security.model.User;
import com.reneuby.security.repository.UserDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findUserByName(String name) {
        return entityManager.createQuery("Select u from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public boolean existsByName(String name) {
        return entityManager
                .createQuery("select count(u) from User u where u.name =:name")
                .setParameter("name", name)
                .getSingleResult().equals(0L) ? false : true;
    }

    @Override
    public boolean existsByEmail(String email) {
        return entityManager
                .createQuery("select count(u) from User u where u.email =:email")
                .setParameter("email", email)
                .getSingleResult().equals(0L) ? false : true;
    }

    @Override
    public void deleteUserByName(String name) {
        entityManager.remove(findUserByName(name));
    }

    @Override
    public User saveUser(User user) {
        entityManager.persist(user);
        return findUserByName(user.getName());
    }
}
