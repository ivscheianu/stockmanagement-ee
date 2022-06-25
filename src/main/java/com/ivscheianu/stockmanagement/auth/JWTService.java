package com.ivscheianu.stockmanagement.auth;

import java.util.Set;

public interface JWTService {
    String createToken(final String username, final Set<String> authorities);
    JWTCredential getCredential(final String token);
    boolean isValidToken(final String authToken);
}
