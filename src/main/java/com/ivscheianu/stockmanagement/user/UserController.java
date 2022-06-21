package com.ivscheianu.stockmanagement.user;

import com.ivscheianu.base.controller.AbstractEntityController;
import com.ivscheianu.base.service.EntityService;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Stateless
@Path("users")
public class UserController extends AbstractEntityController<Long, UserDTO> {

    @Inject
    private UserService userService;

    @Override
    protected EntityService<Long, UserDTO> getService() {
        return userService;
    }

    @Override
    public UserDTO getById(final Long id) {
        throw new WebApplicationException(Response.Status.FORBIDDEN);
    }

    @Override
    public List<UserDTO> getAll() {
        throw new WebApplicationException(Response.Status.NOT_IMPLEMENTED);
    }

    @Override
    public UserDTO update(final UserDTO dataTransferObject) {
        throw new WebApplicationException(Response.Status.NOT_IMPLEMENTED);
    }

    @Override
    public void deleteById(final Long id) {
        throw new WebApplicationException(Response.Status.NOT_IMPLEMENTED);
    }
}
