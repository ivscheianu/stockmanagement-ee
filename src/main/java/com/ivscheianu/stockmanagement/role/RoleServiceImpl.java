package com.ivscheianu.stockmanagement.role;

import com.ivscheianu.base.persistence.EntityRepository;
import com.ivscheianu.base.service.AbstractEntityService;
import com.ivscheianu.base.service.EntityMapper;
import com.ivscheianu.stockmanagement.user.UserService;

import java.util.Set;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Stateless
@Transactional
public class RoleServiceImpl extends AbstractEntityService<Long, RoleDTO, RoleDO> implements RoleService {

    @Inject
    private RoleRepository roleRepository;

    @Inject
    private RoleMapper roleMapper;

    @Inject
    private UserService userService;

    @Override
    protected EntityRepository<Long, RoleDO> getRepository() {
        return roleRepository;
    }

    @Override
    protected EntityMapper<RoleDTO, RoleDO> getMapper() {
        return roleMapper;
    }

    @Override
    public Set<RoleDTO> getCurrentUserRoles() {
        return userService.getCurrentUser().getRoles();
    }

    @Override
    public Set<String> getUserRolesAsString(final String username) {
        return userService
            .getByUsername(username)
            .getRoles()
            .stream()
            .map(RoleDTO::getLabel)
            .collect(Collectors.toSet());
    }
}
