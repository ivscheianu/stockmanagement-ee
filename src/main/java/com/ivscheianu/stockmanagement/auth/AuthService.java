package com.ivscheianu.stockmanagement.auth;

import java.util.Optional;

public interface AuthService {
    Optional<String> tryToLogin(final LoginRequest loginRequest);
}
