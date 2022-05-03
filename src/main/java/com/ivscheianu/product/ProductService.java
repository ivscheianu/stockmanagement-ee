package com.ivscheianu.product;

import com.ivscheianu.base.service.EntityService;

public interface ProductService extends EntityService<Long, ProductDto> {
    ProductDto getByBarcode(final String barcode);
}
