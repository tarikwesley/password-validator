package com.devturing.password.validator.service;

import static org.junit.jupiter.api.Assertions.*;

import com.devturing.password.validator.exception.PasswordInvalidException;
import org.junit.jupiter.api.Test;

class PasswordValidatorServiceTest {

    private final PasswordValidatorService passwordValidatorService = new PasswordValidatorService();

    @Test
    void shouldVerifyPasswordIsValid() {
        assertThrows(PasswordInvalidException.class, () -> {
            passwordValidatorService.isValid("");
        });
        assertThrows(PasswordInvalidException.class, () -> {
            passwordValidatorService.isValid("aa");
        });
        assertThrows(PasswordInvalidException.class, () -> {
            passwordValidatorService.isValid("ab");
        });
        assertThrows(PasswordInvalidException.class, () -> {
            passwordValidatorService.isValid("AAAbbbCc");
        });
        assertThrows(PasswordInvalidException.class, () -> {
            passwordValidatorService.isValid("AbTp9!foo");
        });
        assertThrows(PasswordInvalidException.class, () -> {
            passwordValidatorService.isValid("AbTp9!foA");
        });
        assertThrows(PasswordInvalidException.class, () -> {
            passwordValidatorService.isValid("AbTp9 fok");
        });
        assertThrows(PasswordInvalidException.class, () -> {
            passwordValidatorService.isValid("         ");
        });
        assertTrue(passwordValidatorService.isValid("AbTp9!fok").isValid());
        assertTrue(passwordValidatorService.isValid("Senha123@").isValid());
        assertTrue(passwordValidatorService.isValid("sedKyAm0!").isValid());
    }
}