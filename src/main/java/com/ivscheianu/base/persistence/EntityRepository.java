package com.ivscheianu.base.persistence;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface EntityRepository<IdType extends Serializable, DoType extends AbstractDO<IdType>> {
    DoType save(final DoType databaseObject);
    Optional<DoType> getById(final IdType id);
    List<DoType> getAll();
    DoType update(final DoType databaseObject);
    void deleteById(final IdType id);
}
