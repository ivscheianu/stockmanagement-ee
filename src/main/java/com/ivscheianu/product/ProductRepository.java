package com.ivscheianu.product;

import com.ivscheianu.base.repository.EntityRepository;

public interface ProductRepository extends EntityRepository<Long, ProductDo> {
    ProductDo getByBarcode(final String barcode);
}
