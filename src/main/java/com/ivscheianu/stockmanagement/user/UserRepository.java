package com.ivscheianu.stockmanagement.user;

import com.ivscheianu.base.persistence.EntityRepository;

import java.util.Optional;

public interface UserRepository extends EntityRepository<Long, UserDO> {
    Optional<UserDO> findByUsername(final String username);
}
