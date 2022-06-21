package com.ivscheianu.base.service;

import com.ivscheianu.base.persistence.AbstractDO;

import java.util.Collection;
import java.util.List;

public interface EntityMapper<DtoType extends AbstractDTO<?>, DoType extends AbstractDO<?>> {
    DtoType toDTO(final DoType databaseObject);
    DoType toDO(final DtoType dataTransferObject);
    List<DtoType> toDTO(final Collection<DoType> databaseObjects);
    List<DoType> toDO(final Collection<DtoType> dataTransferObjects);
}
