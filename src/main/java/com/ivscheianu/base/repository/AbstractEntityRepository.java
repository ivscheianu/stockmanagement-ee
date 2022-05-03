package com.ivscheianu.base.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractEntityRepository<IdType extends Serializable, DoType extends AbstractDo<IdType>> implements EntityRepository<IdType, DoType> {

    @PersistenceContext
    protected EntityManager entityManager;
    protected CriteriaBuilder criteriaBuilder;
    protected CriteriaQuery<DoType> criteriaQuery;
    protected Root<DoType> root;
    protected Class<DoType> doClass;

    @PostConstruct
    @SuppressWarnings("unchecked")
    private void postConstruct() {
        doClass = (Class<DoType>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        criteriaBuilder = entityManager.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(doClass);
        root = criteriaQuery.from(doClass);
    }

    @Override
    public DoType save(final DoType databaseObject) {
        return entityManager.merge(databaseObject);
    }

    @Override
    public DoType get(final IdType id) {
        return entityManager.find(doClass, id);
    }

    @Override
    public List<DoType> getAll() {
        final Root<DoType> root = criteriaQuery.from(doClass);
        final CriteriaQuery<DoType> getAll = criteriaQuery.select(root);
        return entityManager.createQuery(getAll).getResultList();
    }

    @Override
    public DoType update(final DoType databaseObject) {
        return save(databaseObject);
    }

    @Override
    public void delete(final IdType id) {
        final DoType doToBeDeleted = get(id);
        entityManager.remove(doToBeDeleted);
    }
}
