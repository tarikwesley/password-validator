package com.devturing.password.validator.controller;

import com.devturing.password.validator.model.PasswordValidationRequest;
import com.devturing.password.validator.model.PasswordValidationResponse;
import com.devturing.password.validator.service.PasswordValidatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/validate-password")
public class PasswordValidatorController {

    private final PasswordValidatorService passwordValidatorService;

    public PasswordValidatorController(PasswordValidatorService passwordValidatorService) {
        this.passwordValidatorService = passwordValidatorService;
    }

    @PostMapping
    public ResponseEntity<PasswordValidationResponse> validatePassword(@RequestBody PasswordValidationRequest request) {
        PasswordValidationResponse isValid = passwordValidatorService.isValid(request.getPassword());
        return ResponseEntity.ok(isValid);
    }
}
