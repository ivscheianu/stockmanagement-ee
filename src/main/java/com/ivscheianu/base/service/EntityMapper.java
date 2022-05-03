package com.ivscheianu.base.service;

import com.ivscheianu.base.repository.AbstractDo;

import java.util.Collection;
import java.util.List;

public interface EntityMapper<DtoType extends AbstractDto<?>, DoType extends AbstractDo<?>> {
    DtoType toDto(final DoType databaseObject);
    DoType toDo(final DtoType dataTransferObject);
    List<DtoType> toDto(final Collection<DoType> databaseObjects);
    List<DoType> toDo(final Collection<DtoType> dataTransferObjects);
}
