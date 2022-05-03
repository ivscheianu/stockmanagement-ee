package com.ivscheianu.base.service;

import java.io.Serializable;
import java.util.List;

public interface EntityService<IdType extends Serializable, DtoType extends AbstractDto<IdType>> {
    DtoType save(final DtoType dataTransferObject);
    DtoType get(final IdType id);
    List<DtoType> getAll();
    DtoType update(final DtoType dataTransferObject);
    void delete(final IdType id);
}
