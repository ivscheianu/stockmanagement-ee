package com.ivscheianu.product;

import com.ivscheianu.base.service.AbstractEntityMapper;

import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Stateless
@Transactional
public class ProductMapper extends AbstractEntityMapper<ProductDto, ProductDo> {

}
