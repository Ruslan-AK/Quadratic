package com.reneuby.validators.impl;

import com.reneuby.domain.CoefString;
import com.reneuby.domain.Coefficients;
import com.reneuby.exceptions.BusinessException;
import com.reneuby.validators.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public Coefficients validateAndGetCoefficients(CoefString coefString) throws BusinessException {
        double a = validateCoef(coefString.getA());
        double b = validateCoef(coefString.getB());
        double c = validateCoef(coefString.getC());
        return new Coefficients(a, b, c);
    }

    private double validateCoef(String test) throws BusinessException {
        if ("".equals(test)) {
            throw new BusinessException("Коэффициенты не могут быть 0");
        }
        test = test.trim().replace(',', '.');
        double d;
        try {
            d = Double.parseDouble(test);
        } catch (Exception e) {
            throw new BusinessException("Вводить можно только числа!");
        }
        if (d == 0) {
            throw new BusinessException("Коэффициенты не могут быть 0");
        }
        return d;
    }
}