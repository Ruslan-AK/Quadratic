package com.reneuby.validators;

import com.reneuby.domain.CoefString;
import com.reneuby.domain.Coefficients;
import com.reneuby.exceptions.BusinessException;

public interface ValidationService {

    Coefficients validateAndGetCoefficients(CoefString input) throws BusinessException;
}
