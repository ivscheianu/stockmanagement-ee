package com.ivscheianu.stockmanagement.role;

import com.ivscheianu.base.service.EntityService;

import java.util.Set;

public interface RoleService extends EntityService<Long, RoleDTO> {
    Set<RoleDTO> getCurrentUserRoles();
    Set<String> getUserRolesAsString(final String username);
}
