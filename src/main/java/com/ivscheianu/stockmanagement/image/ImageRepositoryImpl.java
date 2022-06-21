package com.ivscheianu.stockmanagement.image;

import com.ivscheianu.base.persistence.AbstractEntityRepository;

import javax.ejb.Stateless;

@Stateless
public class ImageRepositoryImpl extends AbstractEntityRepository<Long, ImageDO> implements ImageRepository {

}
