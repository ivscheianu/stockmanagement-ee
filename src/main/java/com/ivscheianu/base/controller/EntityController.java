package com.ivscheianu.base.controller;

import com.ivscheianu.base.service.AbstractDTO;

import java.io.Serializable;
import java.util.List;

public interface EntityController<IdType extends Serializable, DtoType extends AbstractDTO<IdType>>  {
    DtoType save(final DtoType dataTransferObject);
    DtoType getById(final IdType id);
    List<DtoType> getAll();
    DtoType update(final DtoType dataTransferObject);
    void deleteById(final IdType id);
}
