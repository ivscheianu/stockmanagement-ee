package com.ivscheianu.base.repository;

import java.io.Serializable;
import java.util.List;

public interface EntityRepository<IdType extends Serializable, DoType extends AbstractDo<IdType>> {
    DoType save(final DoType databaseObject);
    DoType getById(final IdType id);
    List<DoType> getAll();
    DoType update(final DoType databaseObject);
    void deleteById(final IdType id);
}
