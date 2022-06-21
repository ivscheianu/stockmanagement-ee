package com.ivscheianu.base.persistence;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * CRUD ready repository/DAO, you just need to extend and parameterize it.
 *
 * @param <IdType>  the type of the id
 * @param <DoType>  the type of the Database Object, mapping the DB table
 */

@Slf4j
public abstract class AbstractEntityRepository<IdType extends Serializable, DoType extends AbstractDO<IdType>> implements EntityRepository<IdType, DoType> {

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
        entityManager.persist(databaseObject);
        return databaseObject;
    }

    @Override
    public Optional<DoType> getById(final IdType id) {
        return execute(() -> {
            final DoType doType = entityManager.find(doClass, id);
            return Optional.of(doType);
        });
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
    public boolean deleteById(final IdType id) {
        final Optional<DoType> databaseObjectToBeDeleted = getById(id);
        databaseObjectToBeDeleted.ifPresent((databaseObject) -> entityManager.remove(databaseObject));
        return databaseObjectToBeDeleted.isPresent();
    }

    protected JPAQuery<DoType> newJpaQuery() {
        return new JPAQuery<>(entityManager);
    }

    protected Optional<DoType> execute(final GetOptionalOperation<DoType> getOptionalOperation) {
        try {
            return getOptionalOperation.execute();
        } catch (final NoResultException noResultException) {
            log.error("Failed to find entity", noResultException);
        } catch (final Exception exception) {
            log.error("Unexpected error occurred while executing db operation", exception);
        }
        return Optional.empty();
    }

    protected List<DoType> execute(final GetListOperation<DoType> getListOperation) {
        try {
            return getListOperation.execute();
        } catch (final NoResultException noResultException) {
            log.error("Failed to find entity", noResultException);
        } catch (final Exception exception) {
            log.error("Unexpected error occurred while executing db operation", exception);
        }
        return Collections.emptyList();
    }
}
