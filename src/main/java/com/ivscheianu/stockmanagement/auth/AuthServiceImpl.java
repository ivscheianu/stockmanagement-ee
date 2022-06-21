package com.ivscheianu.stockmanagement.auth;

import static java.util.Objects.nonNull;

import java.util.Objects;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;

@Stateless
public class AuthServiceImpl implements AuthService {

    @Inject
    private JWTService jwtService;

    @Inject
    private IdentityStoreHandler identityStoreHandler;

    @Override
    public Optional<String> tryToLogin(final LoginRequest loginRequest) {
        if (isValidLoginRequest(loginRequest)) {
            final CredentialValidationResult result =
                identityStoreHandler.validate(new UsernamePasswordCredential(loginRequest.getUsername(), loginRequest.getPassword()));
            if (Objects.equals(result.getStatus(), CredentialValidationResult.Status.VALID)) {
                final String jwt = jwtService.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups());
                return Optional.of(jwt);
            }
        }
        return Optional.empty();
    }

    private boolean isValidLoginRequest(final LoginRequest loginRequest) {
        return nonNull(loginRequest) && nonNull(loginRequest.getUsername()) && nonNull(loginRequest.getPassword());
    }
}
