package com.devturing.password.validator.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.devturing.password.validator.exception.PasswordInvalidException;
import com.devturing.password.validator.model.PasswordValidationRequest;
import com.devturing.password.validator.model.PasswordValidationResponse;
import com.devturing.password.validator.service.PasswordValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidatorHandler implements RequestHandler<PasswordValidationRequest, PasswordValidationResponse> {

    private final PasswordValidatorService passwordValidatorService;

    @Autowired
    public PasswordValidatorHandler(PasswordValidatorService passwordValidatorService) {
        this.passwordValidatorService = passwordValidatorService;
    }

    public PasswordValidatorHandler() {
        this.passwordValidatorService = new PasswordValidatorService();
    }

    @Override
    public PasswordValidationResponse handleRequest(PasswordValidationRequest request, Context context) {
        var logger = context.getLogger();
        try {
            logger.log("Request received - " + request);
            return passwordValidatorService.isValid(request.getPassword());
        } catch (PasswordInvalidException e) {
            return new PasswordValidationResponse(false, e.getMessage());
        }
    }
}