package com.reneuby.controllers;

import com.reneuby.webapi.WebApiCoeff;
import com.reneuby.domain.Coefficients;
import com.reneuby.domain.Equation;
import com.reneuby.domain.Roots;
import com.reneuby.exceptions.BusinessException;
import com.reneuby.logic.CalcRoots;
import com.reneuby.services.EquationService;
import com.reneuby.validators.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class MainController {
    private EquationService equationService;
    private ValidationService validationService;

    @Autowired
    public MainController(EquationService equationService, ValidationService validationService) {
        this.equationService = equationService;
        this.validationService = validationService;
    }

    @GetMapping(value = "/")
    @PreAuthorize("hasRole('USER')")
    public String getPage() {
        return "index";
    }

    @PostMapping(value = "/calculate")
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public Roots calculate(@RequestBody WebApiCoeff webApiCoeff) {
        Roots roots = new Roots();
        Coefficients coefficients = null;
        try {
            coefficients = validationService.validateAndGetCoefficients(webApiCoeff);
            roots = CalcRoots.calcRoots(coefficients);
        } catch (BusinessException e) {
            roots.setError(e.getMessage());
            return roots;
        }
        equationService.saveEquation(new Equation(coefficients, roots));
        return roots;
    }

    @GetMapping(value = "/showPreviewEquations")
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public List<String> showPreviewEquations() {
        List<String> responses = new ArrayList<>();
        for (Equation e : equationService.getAllEquations()) {
            responses.add(e.toString());
        }
        return responses;
    }
}
