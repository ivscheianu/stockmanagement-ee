package com.ivscheianu.base.controller;

import com.ivscheianu.base.service.AbstractDTO;
import com.ivscheianu.base.service.EntityService;
import com.ivscheianu.stockmanagement.role.RoleEnum;

import java.io.Serializable;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public abstract class AbstractEntityController<IdType extends Serializable, DtoType extends AbstractDTO<IdType>> implements EntityController<IdType, DtoType> {

    protected abstract EntityService<IdType, DtoType> getService();

    @POST
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({RoleEnum.Constants.ROLE_USER})
    public DtoType save(final DtoType dataTransferObject) {
        return getService().save(dataTransferObject);
    }

    @GET
    @Override
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({RoleEnum.Constants.ROLE_USER})
    public DtoType getById(@PathParam("id") final IdType id) {
        return getService().getById(id);
    }

    @GET
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({RoleEnum.Constants.ROLE_USER})
    public List<DtoType> getAll() {
        return getService().getAll();
    }

    @PUT
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({RoleEnum.Constants.ROLE_USER})
    public DtoType update(final DtoType dataTransferObject) {
        return getService().update(dataTransferObject);
    }

    @DELETE
    @Override
    @Path("/{id}")
    @RolesAllowed({RoleEnum.Constants.ROLE_USER})
    public void deleteById(@PathParam("id") final IdType id) {
        getService().deleteById(id);
    }
}
