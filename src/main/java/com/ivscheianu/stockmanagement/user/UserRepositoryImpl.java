package com.ivscheianu.stockmanagement.user;

import com.ivscheianu.base.persistence.AbstractEntityRepository;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

@Stateless
public class UserRepositoryImpl extends AbstractEntityRepository<Long, UserDO> implements UserRepository {

    private static final String USER_NAME = "userName";

    @Override
    public Optional<UserDO> findByUsername(final String username) {
        return execute(() -> {
            final Predicate isEqual = criteriaBuilder.equal(root.get(USER_NAME), username);
            final CriteriaQuery<UserDO> query = criteriaQuery.select(root).where(isEqual);
            final UserDO result = entityManager.createQuery(query).getSingleResult();
            return Optional.of(result);
        });
    }
}
