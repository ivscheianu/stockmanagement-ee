package com.ivscheianu.stockmanagement.product;

import com.ivscheianu.base.persistence.EntityRepository;

import java.util.Optional;

public interface ProductRepository extends EntityRepository<Long, ProductDO> {
    Optional<ProductDO> getByBarcode(final String barcode);
}
