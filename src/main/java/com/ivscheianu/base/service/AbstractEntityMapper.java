package com.ivscheianu.base.service;

import com.ivscheianu.base.HasId;
import com.ivscheianu.base.persistence.AbstractDO;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

/**
 * Abstract class for transforming any DTO to DO and vice versa. You just need to extend and parameterize it. You can
 * obliviously call super and extend the functionality or rewrite it from scratch on need.
 *
 * @param <DtoType> the type of the Data Transfer Object, the one expose to the external world
 * @param <DoType>  the type of the Database Object, mapping the DB table
 */

public abstract class AbstractEntityMapper<DtoType extends AbstractDTO<?>, DoType extends AbstractDO<?>> implements EntityMapper<DtoType, DoType> {

    private final ModelMapper modelMapper = new ModelMapper();

    private Class<DtoType> dtoType;
    private Class<DoType> doType;

    @PostConstruct
    @SuppressWarnings("unchecked")
    private void postConstruct() {
        dtoType = (Class<DtoType>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        doType = (Class<DoType>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Override
    public DtoType toDTO(final DoType databaseObject) {
        return modelMapper.map(databaseObject, dtoType);
    }

    @Override
    public DoType toDO(final DtoType dataTransferObject) {
        return modelMapper.map(dataTransferObject, doType);
    }

    @Override
    public List<DtoType> toDTO(final Collection<DoType> databaseObjects) {
        return CollectionUtils
            .emptyIfNull(databaseObjects)
            .stream()
            .filter(Objects::nonNull)
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<DoType> toDO(final Collection<DtoType> dataTransferObjects) {
        return CollectionUtils
            .emptyIfNull(dataTransferObjects)
            .stream()
            .filter(Objects::nonNull)
            .map(this::toDO)
            .collect(Collectors.toList());
    }

    protected <T extends Serializable> Stream<T> extractIdsAsStream(final Collection<? extends HasId<T>> objectsWithId) {
        return objectsWithId
            .stream()
            .filter(Objects::nonNull)
            .map(HasId::getId);
    }

    protected <T extends Serializable> Set<T> extractIdsAsSet(final Collection<? extends HasId<T>> objectsWithId) {
        return extractIdsAsStream(objectsWithId).collect(Collectors.toSet());
    }

    protected <T extends Serializable> List<T> extractIdsAsList(final Collection<? extends HasId<T>> objectsWithId) {
        return extractIdsAsStream(objectsWithId).collect(Collectors.toList());
    }
}
