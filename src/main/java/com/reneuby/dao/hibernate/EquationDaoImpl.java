package com.reneuby.dao.hibernate;

import com.reneuby.dao.EquationDao;
import com.reneuby.domain.Equation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EquationDaoImpl implements EquationDao {

    @PersistenceContext
    EntityManager manager;

    @Transactional
    public long saveEquation(Equation equation) {
        manager.persist(equation);
        return equation.getId();
    }

    public List<Equation> getAllEquations() {
        return manager.createQuery("Select e from Equation e " +
                "LEFT JOIN FETCH e.coefficients " +
                "LEFT JOIN FETCH e.roots").getResultList();
    }

    public void deleteEquation(long id) {
        manager.remove(getEquation(id));
    }

    public boolean isPresent(Equation equation) {
        return manager.contains(equation);
    }

    public Equation getEquation(long id) {
        return manager.find(Equation.class, id);
    }
}
