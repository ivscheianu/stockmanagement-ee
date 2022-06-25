package com.ivscheianu.stockmanagement.auth;

import static java.util.Objects.nonNull;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.SneakyThrows;

import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestScoped
public class JWTAuthenticationMechanism implements HttpAuthenticationMechanism {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER = "Bearer ";

    @Inject
    private JWTService jwtService;

    @Override
    @SneakyThrows
    public AuthenticationStatus validateRequest(final HttpServletRequest request,
                                                final HttpServletResponse response,
                                                final HttpMessageContext context) {
        if (context.isProtected()) {
            return getAuthenticationStatusFromJWT(context);
        } else {
            return context.doNothing();
        }
    }

    private AuthenticationStatus getAuthenticationStatusFromJWT(final HttpMessageContext context) {
        final Optional<String> token = extractToken(context);
        if (token.isPresent()) {
            return validateToken(token.get(), context);
        } else {
            return context.responseUnauthorized();
        }
    }

    private AuthenticationStatus validateToken(final String token, final HttpMessageContext context) {
        try {
            if (jwtService.isValidToken(token)) {
                final JWTCredential credential = jwtService.getCredential(token);
                return context.notifyContainerAboutLogin(credential.getPrincipal(), credential.getAuthorities());
            }
            return context.responseUnauthorized();
        } catch (final ExpiredJwtException eje) {
            return context.responseUnauthorized();
        }
    }

    private Optional<String> extractToken(final HttpMessageContext context) {
        final String authorizationHeader = context.getRequest().getHeader(AUTHORIZATION_HEADER);
        if (nonNull(authorizationHeader) && authorizationHeader.startsWith(BEARER)) {
            final String token = authorizationHeader.substring(BEARER.length());
            return Optional.of(token);
        }
        return Optional.empty();
    }
}
