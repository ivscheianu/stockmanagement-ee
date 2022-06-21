package com.ivscheianu.stockmanagement.bucket;

import com.ivscheianu.base.service.EntityService;
import com.ivscheianu.stockmanagement.user.UserDTO;

public interface BucketService extends EntityService<Long, BucketDTO> {
    BucketDTO buildBucketForNewUser(final UserDTO user);
}
