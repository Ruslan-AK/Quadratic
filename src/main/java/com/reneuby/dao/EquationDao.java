package com.reneuby.dao;

import com.reneuby.domain.Equation;

import java.util.List;

public interface EquationDao {
    long saveEquation(Equation equation);

    List<Equation> getAllEquations();

    void deleteEquation(long id);

    boolean isPresent(Equation equation);

    Equation getEquation(long id);
}
