package com.ivscheianu.base.service;

import java.io.Serializable;
import java.util.List;

public interface EntityService<IdType extends Serializable, DtoType extends AbstractDTO<IdType>> {
    DtoType save(final DtoType dataTransferObject);
    DtoType getById(final IdType id);
    List<DtoType> getAll();
    DtoType update(final DtoType dataTransferObject);
    void deleteById(final IdType id);
}
