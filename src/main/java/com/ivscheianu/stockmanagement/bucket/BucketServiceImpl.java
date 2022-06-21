package com.ivscheianu.stockmanagement.bucket;

import com.ivscheianu.base.persistence.EntityRepository;
import com.ivscheianu.base.service.AbstractEntityService;
import com.ivscheianu.base.service.EntityMapper;
import com.ivscheianu.common.blob.s3.S3StorageBroker;
import com.ivscheianu.stockmanagement.user.UserDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Stateless
@Transactional
public class BucketServiceImpl extends AbstractEntityService<Long, BucketDTO, BucketDO> implements BucketService {

    private static final String BUCKET_PREFIX = "stockmanagement-";

    @Inject
    private BucketRepository bucketRepository;

    @Inject
    private BucketMapper bucketMapper;

    @Inject
    private S3StorageBroker s3StorageBroker;

    @Override
    protected EntityRepository<Long, BucketDO> getRepository() {
        return bucketRepository;
    }

    @Override
    protected EntityMapper<BucketDTO, BucketDO> getMapper() {
        return bucketMapper;
    }

    @Override
    public BucketDTO buildBucketForNewUser(final UserDTO user) {
        final String bucketName = BUCKET_PREFIX + user.getUserName();
        s3StorageBroker.createBucket(bucketName);
        return BucketDTO
            .builder()
            .user(user)
            .name(bucketName)
            .build();
    }
}
