package com.ivscheianu.stockmanagement.stock;

import com.ivscheianu.base.service.EntityService;

public interface StockService extends EntityService<Long, StockDTO> {
    StockDTO addStockForProduct(final StockDTO stockDTO, final String barcode);
}
