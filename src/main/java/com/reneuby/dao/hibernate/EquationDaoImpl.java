package com.reneuby.dao.hibernate;

import com.reneuby.dao.EquationDao;
import com.reneuby.domain.Equation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EquationDaoImpl implements EquationDao {
    private HibernateSessionFactoryUtil factory;

    @Autowired
    public EquationDaoImpl(HibernateSessionFactoryUtil factory) {
        this.factory = factory;
    }


    public long saveEquation(Equation equation) {
        Session session = factory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(equation);
        tx1.commit();
        session.close();
        return equation.getId();
    }

    public List<Equation> getAllEquations() {
        List<Equation> users = (List<Equation>) factory.getSessionFactory().openSession().createQuery("From Equation ").list();
        return users;
    }

    public void deleteEquation(long id) {
        Session session = factory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(getEquation(id));
        tx1.commit();
        session.close();
    }

    public boolean isPresent(Equation equation) {
        return factory.getSessionFactory().openSession().contains(equation);
    }

    public Equation getEquation(long id) {
        return factory.getSessionFactory().openSession().get(Equation.class, id);
    }
}
