package com.reneuby.controllers;

import com.reneuby.webapi.WebApiCoeff;
import com.reneuby.domain.Coefficients;
import com.reneuby.domain.Equation;
import com.reneuby.domain.Roots;
import com.reneuby.services.EquationService;
import com.reneuby.validators.ValidationService;
import com.reneuby.validators.impl.ValidationServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {
    ValidationService validationService = new ValidationServiceImpl();
    @Mock
    private EquationService mockEquationService;

    @Test
    public void createEquationRightParameters() {
        WebApiCoeff coefficients = new WebApiCoeff();
        coefficients.setA("2");
        coefficients.setB("3");
        coefficients.setC("-9");
        MainController mainController = new MainController(mockEquationService, validationService);
        Roots roots = mainController.calculate(coefficients);
        Roots roots1 = new Roots(1.5, -3.0);
        Assert.assertEquals(roots, roots1);
    }

    @Test
    public void createEquationWrongParameters() {
        WebApiCoeff coefficients = new WebApiCoeff();
        coefficients.setA("2");
        coefficients.setB("3");
        coefficients.setC("9");
        MainController mainController = new MainController(mockEquationService, validationService);
        Roots roots = mainController.calculate(coefficients);
        String errorMessage = "Не существует корней для данных коэффициентов";
        Assert.assertEquals(roots.getError(), errorMessage);
    }

    @Test
    public void saveEquationIfRightCoeff() {
        WebApiCoeff coefficients = new WebApiCoeff();
        coefficients.setA("2");
        coefficients.setB("3");
        coefficients.setC("-9");
        MainController mainController = new MainController(mockEquationService, validationService);
        Roots roots = mainController.calculate(coefficients);
        Coefficients coeff = new Coefficients(2, 3, -9);
        Roots roots1 = new Roots(1.5, -3);
        Mockito.verify(mockEquationService).saveEquation(new Equation(coeff, roots1));
    }

    @Test
    public void dontSaveEquationIfWrongCoeff() {
        WebApiCoeff coefficients = new WebApiCoeff();
        coefficients.setA("2");
        coefficients.setB("3");
        coefficients.setC("9");
        MainController mainController = new MainController(mockEquationService, validationService);
        Roots roots = mainController.calculate(coefficients);
        Coefficients coeff = new Coefficients(2, 3, 9);
        Roots roots1 = new Roots(1.5, -3);
        Mockito.verify(mockEquationService, times(0))
                .saveEquation(new Equation(coeff, roots1));
    }
}