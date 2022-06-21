package com.ivscheianu.stockmanagement.auth;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Stateless
@Path("auth")
public class AuthController {

    @Inject
    private AuthService authService;

    @POST
    @Path("login")
    public Response login(final LoginRequest loginRequest) {
        return authService.tryToLogin(loginRequest)
            .map(jwt -> Response.ok(jwt).build())
            .orElse(Response.status(UNAUTHORIZED).build());
    }
}
