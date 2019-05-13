package com.reneuby.logic;

import com.reneuby.domain.Coefficients;
import com.reneuby.domain.Roots;
import com.reneuby.exceptions.BusinessException;

public class CalcRoots {

    public static Roots calcRoots(Coefficients coefficients) throws BusinessException {
        double a, b, c, d, x1, x2;
        a = coefficients.getA();
        b = coefficients.getB();
        c = coefficients.getC();
        d = Math.pow(b, 2) - 4 * a * c;
        if (d >= 0) {
            x1 = (-b + Math.sqrt(d)) / (2 * a);
            x2 = (-b - Math.sqrt(d)) / (2 * a);
            return new Roots(x1, x2);
        } else {
            throw new BusinessException("Не существует корней для данных коэффициентов");
        }
    }
}
