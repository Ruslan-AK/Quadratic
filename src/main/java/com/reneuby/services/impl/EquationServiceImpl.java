package com.reneuby.services.impl;

import com.reneuby.dao.EquationDao;
import com.reneuby.domain.Equation;
import com.reneuby.services.EquationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquationServiceImpl implements EquationService {
    private EquationDao equationDao;

    @Autowired
    public EquationServiceImpl(EquationDao equationDao) {
        this.equationDao = equationDao;
    }

    public long saveEquation(Equation equation) {
        return equationDao.saveEquation(equation);
    }

    public List<Equation> getAllEquations() {
        return equationDao.getAllEquations();
    }

    public void deleteEquation(long id) {
        equationDao.deleteEquation(id);
    }

    public boolean isPresent(Equation equation) {
        return equationDao.isPresent(equation);
    }

    public Equation getEquation(long id) {
        return equationDao.getEquation(id);
    }
}
