package com.ivscheianu.stockmanagement.stock;

import com.ivscheianu.base.persistence.EntityRepository;
import com.ivscheianu.base.service.AbstractEntityService;
import com.ivscheianu.base.service.EntityMapper;
import com.ivscheianu.stockmanagement.product.ProductDTO;
import com.ivscheianu.stockmanagement.product.ProductService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Stateless
@Transactional
public class StockServiceImpl extends AbstractEntityService<Long, StockDTO, StockDO> implements StockService {

    @Inject
    private StockRepository stockRepository;

    @Inject
    private StockMapper stockMapper;

    @Inject
    private ProductService productService;

    @Override
    protected EntityRepository<Long, StockDO> getRepository() {
        return stockRepository;
    }

    @Override
    protected EntityMapper<StockDTO, StockDO> getMapper() {
        return stockMapper;
    }

    @Override
    public StockDTO addStockForProduct(final StockDTO stock, final String barcode) {
        final ProductDTO product = productService.getByBarcode(barcode);
        stock.setProduct(product);
        return save(stock);
    }

    @Override
    public StockDTO update(final StockDTO newVersion) {
        final StockDTO oldStock = getById(newVersion.getId());
        newVersion.setProduct(oldStock.getProduct());
        return super.update(newVersion);
    }
}
