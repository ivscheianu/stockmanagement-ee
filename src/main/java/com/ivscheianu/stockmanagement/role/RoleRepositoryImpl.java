package com.ivscheianu.stockmanagement.role;

import com.ivscheianu.base.persistence.AbstractEntityRepository;
import com.ivscheianu.stockmanagement.user.QUserDO;
import com.querydsl.jpa.impl.JPAQuery;

import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class RoleRepositoryImpl extends AbstractEntityRepository<Long, RoleDO> implements RoleRepository {

    private static final String ID = "id";
    private static final String USERS = "users";

    @Override
    public List<RoleDO> getRolesByUserId(final long userId) {

        final JPAQuery<RoleDO> query = newJpaQuery();
//        return query.from(QUserDO.userDO, QUserDO.userDO)
//            .where(QUserDO.userDO.id.eq(userId))
//            .select(QRoleDO.roleDO)
//            .fetch();
        return query
            .from(QUserDO.userDO)
            .innerJoin(QUserDO.userDO.roles, QRoleDO.roleDO)
            .fetchJoin()
            .where(QUserDO.userDO.id.eq(userId))
            .select(QRoleDO.roleDO)
            .fetch();
    }
}
