package com.ivscheianu.stockmanagement.role;

import com.ivscheianu.base.persistence.EntityRepository;

import java.util.List;

public interface RoleRepository extends EntityRepository<Long, RoleDO> {
    List<RoleDO> getRolesByUserId(final long userId);
}
