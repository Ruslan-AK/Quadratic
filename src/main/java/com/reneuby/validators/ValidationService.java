package com.reneuby.validators;

import com.reneuby.webapi.WebApiCoefficients;
import com.reneuby.domain.Coefficients;
import com.reneuby.exceptions.BusinessException;

public interface ValidationService {

    Coefficients validateAndGetCoefficients(WebApiCoefficients input) throws BusinessException;
}
