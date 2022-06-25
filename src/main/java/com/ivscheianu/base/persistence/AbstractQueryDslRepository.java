package com.ivscheianu.base.persistence;

import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractQueryDslRepository<IdType extends Serializable, DoType extends AbstractDO<IdType>>
    extends AbstractEntityRepository<IdType, DoType> implements EntityRepository<IdType, DoType> {

    protected abstract EntityPathBase<DoType> getEntityModel();

    protected JPAQuery<DoType> newJpaQuery() {
        return new JPAQuery<>(entityManager);
    }

    @Override
    public List<DoType> getAll() {
        final EntityPathBase<DoType> model = getEntityModel();
        return newJpaQuery()
            .from(model)
            .select(model)
            .fetch();
    }
}
