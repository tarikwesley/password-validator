package com.devturing.password.validator.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.devturing.password.validator.model.PasswordValidationRequest;
import com.devturing.password.validator.model.PasswordValidationResponse;
import com.devturing.password.validator.service.PasswordValidatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PasswordValidatorHandlerTest {
    @InjectMocks
    private PasswordValidatorHandler passwordValidatorHandler;

    @Mock
    private PasswordValidatorService passwordValidatorService;

    @Mock
    private Context context;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        passwordValidatorHandler = new PasswordValidatorHandler(passwordValidatorService);
    }


    @Test
    void handleRequest_validPassword() {
        PasswordValidationRequest request = new PasswordValidationRequest("#Senha123");
        when(passwordValidatorService.isValid(request.getPassword())).thenReturn(new PasswordValidationResponse(true, "Password is valid"));

        when(context.getLogger()).thenReturn(mock(LambdaLogger.class));

        PasswordValidationResponse response = passwordValidatorHandler.handleRequest(request, context);

        assertTrue(response.isValid());
        assertEquals("Password is valid", response.getMessage());
    }

    @Test
    void handleRequest_invalidPassword() {
        PasswordValidationRequest request = new PasswordValidationRequest("aaaa");
        when(passwordValidatorService.isValid(request.getPassword())).thenReturn(new PasswordValidationResponse(false, "The password must contain at least 9 characters."));

        when(context.getLogger()).thenReturn(mock(LambdaLogger.class));

        PasswordValidationResponse response = passwordValidatorHandler.handleRequest(request, context);

        assertFalse(response.isValid());
        assertEquals("The password must contain at least 9 characters.", response.getMessage());
    }
}
