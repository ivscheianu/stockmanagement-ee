package com.ivscheianu.base.service;

import com.ivscheianu.base.persistence.AbstractDO;
import com.ivscheianu.base.persistence.EntityRepository;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * CRUD ready Service, just need to extend, parameterize it and provide the specific Mapper and Repository
 *
 * @param <IdType>  the type of the id
 * @param <DtoType> the type of the Data Transfer Object, the one expose to the external world
 * @param <DoType>  the type of the Database Object, mapping the DB table
 */

@Slf4j
public abstract class AbstractEntityService<IdType extends Serializable, DtoType extends AbstractDTO<IdType>, DoType extends AbstractDO<IdType>> implements EntityService<IdType, DtoType> {

    protected abstract EntityRepository<IdType, DoType> getRepository();
    protected abstract EntityMapper<DtoType, DoType> getMapper();

    @Override
    public DtoType save(final DtoType dataTransferObject) {
        final DoType toBeSaved = getMapper().toDO(dataTransferObject);
        final DoType saved = getRepository().save(toBeSaved);
        return getMapper().toDTO(saved);
    }

    @Override
    public DtoType getById(final IdType id) {
        final Optional<DoType> databaseObject = getRepository().getById(id);
        return mapToDto(databaseObject);
    }

    @Override
    public List<DtoType> getAll() {
        final List<DoType> allDtos = getRepository().getAll();
        return getMapper().toDTO(allDtos);
    }

    @Override
    public DtoType update(final DtoType dataTransferObject) {
        final DoType updatedVersion = getMapper().toDO(dataTransferObject);
        final DoType newVersion = getRepository().update(updatedVersion);
        return getMapper().toDTO(newVersion);
    }

    @Override
    public void deleteById(final IdType id) {
        getRepository().deleteById(id);
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    protected DtoType mapToDto(final Optional<DoType> databaseObject) {
        return databaseObject
            .map(entity -> getMapper().toDTO(entity))
            .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }
}
