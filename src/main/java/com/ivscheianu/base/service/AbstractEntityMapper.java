package com.ivscheianu.base.service;

import com.ivscheianu.base.repository.AbstractDo;
import org.modelmapper.ModelMapper;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;


public abstract class AbstractEntityMapper<DtoType extends AbstractDto<?>, DoType extends AbstractDo<?>> implements EntityMapper<DtoType, DoType> {

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
    public DtoType toDto(final DoType databaseObject) {
        return modelMapper.map(databaseObject, dtoType);
    }

    @Override
    public DoType toDo(final DtoType dataTransferObject) {
        return modelMapper.map(dataTransferObject, doType);
    }

    @Override
    public List<DtoType> toDto(final Collection<DoType> databaseObjects) {
        return databaseObjects
            .stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<DoType> toDo(final Collection<DtoType> dataTransferObjects) {
        return dataTransferObjects
            .stream()
            .map(this::toDo)
            .collect(Collectors.toList());
    }
}
