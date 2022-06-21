package com.ivscheianu.stockmanagement.user;

import com.ivscheianu.base.service.EntityService;

public interface UserService extends EntityService<Long, UserDTO> {
    UserDTO getCurrentUser();
    UserDTO getByUsername(final String userName);
}
