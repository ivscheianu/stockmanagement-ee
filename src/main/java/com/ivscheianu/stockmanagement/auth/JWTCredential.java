package com.ivscheianu.stockmanagement.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

import javax.security.enterprise.credential.Credential;

@Data
@AllArgsConstructor
public class JWTCredential implements Credential {
    private final String principal;
    private final Set<String> authorities;
}
