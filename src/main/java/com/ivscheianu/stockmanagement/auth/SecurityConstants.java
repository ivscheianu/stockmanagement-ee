package com.ivscheianu.stockmanagement.auth;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityConstants {
    public static final String AUTH = "auth";
    public static final String LOGIN = "login";
    public static final String SLASH = "/";
    public static final String LOGIN_ENDPOINT = SLASH + AUTH + SLASH + LOGIN;
}
