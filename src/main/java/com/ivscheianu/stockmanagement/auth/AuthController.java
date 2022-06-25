package com.ivscheianu.stockmanagement.auth;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import com.ivscheianu.stockmanagement.user.UserDTO;
import com.ivscheianu.stockmanagement.user.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("auth")
public class AuthController {

    @Inject
    private AuthService authService;

    @Inject
    private UserService userService;

    @POST
    @Path("login")
    @Produces({MediaType.TEXT_PLAIN})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response login(final LoginRequest loginRequest) {
        return authService.tryToLogin(loginRequest)
            .map(jwt -> Response.ok(jwt).build())
            .orElse(Response.status(UNAUTHORIZED).build());
    }

    @POST
    @Path("register")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public UserDTO register(final UserDTO user) {
        return userService.save(user);
    }
}
