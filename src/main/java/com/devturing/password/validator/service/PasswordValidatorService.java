package com.devturing.password.validator.service;

import com.devturing.password.validator.exception.PasswordInvalidException;
import com.devturing.password.validator.model.PasswordValidationResponse;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Service
public class PasswordValidatorService {

    private static final int MIN_LENGTH = 9;
    private static final String DIGIT_REGEX = ".*\\d.*";
    private static final String LOWERCASE_REGEX = ".*[a-z].*";
    private static final String UPPERCASE_REGEX = ".*[A-Z].*";
    private static final String SPECIAL_CHAR_REGEX = ".*[!@#$%^&*()\\-+].*";

    public PasswordValidationResponse isValid(String password) {
        if (password == null || password.contains(" ")) {
            throw new PasswordInvalidException("The password cannot be null or contain space.");
        }
        if (password.length() < MIN_LENGTH) {
            throw new PasswordInvalidException("The password must contain at least 9 characters.");
        }
        if (!Pattern.matches(DIGIT_REGEX, password)) {
            throw new PasswordInvalidException("The password must contain at least one number.");
        }
        if (!Pattern.matches(LOWERCASE_REGEX, password)) {
            throw new PasswordInvalidException("The password must contain at least one lowercase character.");
        }
        if (!Pattern.matches(UPPERCASE_REGEX, password)) {
            throw new PasswordInvalidException("The password must contain at least one uppercase character.");
        }
        if (!Pattern.matches(SPECIAL_CHAR_REGEX, password)) {
            throw new PasswordInvalidException("The password must contain at least one special character(!@#$%^&*()-+).");
        }
        return hasRepeatedCharacters(password);
    }

    private PasswordValidationResponse hasRepeatedCharacters(String password) {
        Set<Character> chars = new HashSet<>();
        for (char c : password.toCharArray()) {
            if (chars.contains(c)) {
                throw new PasswordInvalidException("The password must not have repeated characters.");
            }
            chars.add(c);
        }
        return new PasswordValidationResponse(true, "Password is valid" );
    }
}
