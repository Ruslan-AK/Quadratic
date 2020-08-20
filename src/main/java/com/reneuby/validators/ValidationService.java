package com.reneuby.validators;

import com.reneuby.webapi.WebApiCoeff;
import com.reneuby.domain.Coefficients;
import com.reneuby.exceptions.BusinessException;

public interface ValidationService {

    Coefficients validateAndGetCoefficients(WebApiCoeff input) throws BusinessException;
}
