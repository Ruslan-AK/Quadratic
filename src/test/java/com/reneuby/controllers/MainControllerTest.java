package com.reneuby.controllers;

import com.reneuby.domain.CoefString;
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
        //Given
        CoefString coefficients = new CoefString();
        coefficients.setA("2");
        coefficients.setB("3");
        coefficients.setC("-9");
        //When
        MainController mainController = new MainController(mockEquationService, validationService);
        //calculated
        Roots roots = mainController.calculate(coefficients);
        //right
        Roots roots1 = new Roots(1.5, -3.0);
        //Then
        Assert.assertEquals(roots, roots1);
    }

    @Test
    public void createEquationWrongParameters() {
        //Given
        CoefString coefficients = new CoefString();
        coefficients.setA("2");
        coefficients.setB("3");
        coefficients.setC("9");
        //When
        MainController mainController = new MainController(mockEquationService, validationService);
        //calculated
        Roots roots = mainController.calculate(coefficients);
        //right
        String errorMessage = "Не существует корней для данных коэффициентов";
        //Then
        Assert.assertEquals(roots.getError(), errorMessage);
    }

    @Test
    public void saveEquationIfRightCoeff() {
        //Given
        CoefString coefficients = new CoefString();
        coefficients.setA("2");
        coefficients.setB("3");
        coefficients.setC("-9");
        //When
        MainController mainController = new MainController(mockEquationService, validationService);
        //calculated and save
        Roots roots = mainController.calculate(coefficients);
        //create Equation to check
        Coefficients coeff = new Coefficients(2, 3, -9);
        Roots roots1 = new Roots(1.5, -3);
        //check
        Mockito.verify(mockEquationService).saveEquation(new Equation(coeff, roots1));
    }

    @Test
    public void dontSaveEquationIfWrongCoeff() {
        //Given
        CoefString coefficients = new CoefString();
        coefficients.setA("2");
        coefficients.setB("3");
        coefficients.setC("9");
        //When
        MainController mainController = new MainController(mockEquationService, validationService);
        //calculated and save
        Roots roots = mainController.calculate(coefficients);
        //create Equation to check
        Coefficients coeff = new Coefficients(2, 3, 9);
        Roots roots1 = new Roots(1.5, -3);
        //check
        Mockito.verify(mockEquationService, times(0)).saveEquation(new Equation(coeff, roots1));
    }
}