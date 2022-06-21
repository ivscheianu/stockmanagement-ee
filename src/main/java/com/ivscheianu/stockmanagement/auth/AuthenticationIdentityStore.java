package com.ivscheianu.stockmanagement.auth;

import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import static javax.security.enterprise.identitystore.CredentialValidationResult.NOT_VALIDATED_RESULT;
import static javax.security.enterprise.identitystore.IdentityStore.ValidationType.VALIDATE;

import com.ivscheianu.stockmanagement.user.UserDTO;
import com.ivscheianu.stockmanagement.user.UserService;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

@RequestScoped
public class AuthenticationIdentityStore implements IdentityStore {

    @Inject
    private UserService userService;

    @Override
    public CredentialValidationResult validate(final Credential credential) {
        if (credential instanceof UsernamePasswordCredential) {
            final UsernamePasswordCredential credentials = (UsernamePasswordCredential) credential;
            if (isPasswordMatching(credentials)) {
                return new CredentialValidationResult(credentials.getCaller());
            } else {
                return INVALID_RESULT;
            }
        } else {
            return NOT_VALIDATED_RESULT;
        }
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return Collections.singleton(VALIDATE);
    }

    private boolean isPasswordMatching(final UsernamePasswordCredential credentials) {
        final UserDTO userDTO = userService.getByUsername(credentials.getCaller());
        final String expectedPassword = userDTO.getPassword();
        return Objects.equals(expectedPassword, credentials.getPasswordAsString());
    }
}
