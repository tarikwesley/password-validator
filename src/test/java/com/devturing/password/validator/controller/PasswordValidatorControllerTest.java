package com.devturing.password.validator.controller;

import com.devturing.password.validator.model.PasswordValidationRequest;
import com.devturing.password.validator.exception.PasswordInvalidException;
import com.devturing.password.validator.model.PasswordValidationResponse;
import com.devturing.password.validator.service.PasswordValidatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PasswordValidatorController.class)
class PasswordValidatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PasswordValidatorService passwordValidatorService;

    @BeforeEach
    public void setUp() {
        when(passwordValidatorService.isValid("AbTp9!fok")).thenReturn(new PasswordValidationResponse(true, "Password is valid"));
        when(passwordValidatorService.isValid("AAAbbbCc")).thenThrow(new PasswordInvalidException("The password must not have repeated characters."));
    }

    @Test
    void validPassword() throws Exception {
        PasswordValidationRequest request = new PasswordValidationRequest("AbTp9!fok");
        String jsonContent = objectMapper.writeValueAsString(request);
        PasswordValidationResponse response = new PasswordValidationResponse(true, "Password is valid");
        String jsonResponse = objectMapper.writeValueAsString(response);
        mockMvc.perform(post("/validate-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));
    }

    @Test
    void invalidPassword() throws Exception {
        PasswordValidationRequest password2 = new PasswordValidationRequest("AAAbbbCc");
        String jsonContent = objectMapper.writeValueAsString(password2);
        PasswordValidationResponse response = new PasswordValidationResponse(false, "The password must not have repeated characters.");
        String jsonResponse = objectMapper.writeValueAsString(response);
        mockMvc.perform(post("/validate-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(jsonResponse));
    }
}
