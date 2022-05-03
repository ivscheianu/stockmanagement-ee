package com.ivscheianu.base.controller;

import com.ivscheianu.base.service.AbstractDto;
import com.ivscheianu.base.service.EntityService;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

public abstract class AbstractEntityController<IdType extends Serializable, DtoType extends AbstractDto<IdType>> implements EntityController<IdType, DtoType> {

    protected abstract EntityService<IdType, DtoType> getService();

    @POST
    @Override
    @Consumes("application/json")
    public DtoType save(final DtoType dataTransferObject) {
        return getService().save(dataTransferObject);
    }

    @GET
    @Override
    @Path("/{id}")
    @Produces("application/json")
    public DtoType getById(@PathParam("id") final IdType id) {
        return getService().getById(id);
    }

    @GET
    @Override
    @Produces("application/json")
    public List<DtoType> getAll() {
        return getService().getAll();
    }

    @PUT
    @Override
    @Consumes("application/json")
    public DtoType update(final DtoType dataTransferObject) {
        return getService().update(dataTransferObject);
    }

    @DELETE
    @Override
    public void deleteById(final IdType id) {
        getService().deleteById(id);
    }
}
