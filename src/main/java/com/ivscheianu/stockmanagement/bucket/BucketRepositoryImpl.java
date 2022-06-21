package com.ivscheianu.stockmanagement.bucket;

import com.ivscheianu.base.persistence.AbstractEntityRepository;

import javax.ejb.Stateless;

@Stateless
public class BucketRepositoryImpl extends AbstractEntityRepository<Long, BucketDO> implements BucketRepository {

}
