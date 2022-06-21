package com.ivscheianu.stockmanagement.product;

import com.ivscheianu.base.service.EntityService;

public interface ProductService extends EntityService<Long, ProductDTO> {
    ProductDTO getByBarcode(final String barcode);
}
