package com.reneuby.controllers;

import com.reneuby.domain.CoefString;
import com.reneuby.domain.Coefficients;
import com.reneuby.domain.Equation;
import com.reneuby.domain.Roots;
import com.reneuby.exceptions.BusinessException;
import com.reneuby.logic.CalcRoots;
import com.reneuby.services.EquationService;
import com.reneuby.validators.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private EquationService equationService;
    private ValidationService validationService;

    @Autowired
    public MainController(EquationService equationService, ValidationService validationService) {
        this.equationService = equationService;
        this.validationService = validationService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getPage() {
        return "index";
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    public @ResponseBody
    Roots calculate(@RequestBody CoefString coefString) {
        Roots roots = new Roots();
        Coefficients coefficients = null;
        try {
            coefficients = validationService.validateAndGetCoefficients(coefString);
            roots = CalcRoots.calcRoots(coefficients);
        } catch (BusinessException e) {
            roots.setError(e.getMessage());
            return roots;
        }
        equationService.saveEquation(new Equation(coefficients, roots));
        return roots;
    }

    @RequestMapping(value = "/showPreviewEquations", method = RequestMethod.GET)
    public @ResponseBody
    List<String> showPreviewEquations() {
        List<String> responses = new ArrayList<>();
        for (Equation e : equationService.getAllEquations()) {
            responses.add(e.toString());
        }
        return responses;
    }
}
