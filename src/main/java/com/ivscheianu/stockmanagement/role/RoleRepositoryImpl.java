package com.ivscheianu.stockmanagement.role;

import com.ivscheianu.base.persistence.AbstractQueryDslRepository;
import com.ivscheianu.stockmanagement.user.QUserDO;
import com.querydsl.core.types.dsl.EntityPathBase;

import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class RoleRepositoryImpl extends AbstractQueryDslRepository<Long, RoleDO> implements RoleRepository {

    private static final String ID = "id";
    private static final String USERS = "users";
    public static final QUserDO USER_MODEL = QUserDO.userDO;
    public static final QRoleDO ROLE_MODEL = QRoleDO.roleDO;

    @Override
    public List<RoleDO> getRolesByUserId(final long userId) {
        return newJpaQuery()
            .from(USER_MODEL)
            .innerJoin(USER_MODEL.roles, ROLE_MODEL)
            .fetchJoin()
            .where(USER_MODEL.id.eq(userId))
            .select(ROLE_MODEL)
            .fetch();
    }

    @Override
    protected EntityPathBase<RoleDO> getEntityModel() {
        return ROLE_MODEL;
    }
}
