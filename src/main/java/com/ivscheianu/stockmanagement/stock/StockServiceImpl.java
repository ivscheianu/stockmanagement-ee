package com.ivscheianu.stockmanagement.stock;

import com.ivscheianu.base.persistence.EntityRepository;
import com.ivscheianu.base.service.AbstractEntityService;
import com.ivscheianu.base.service.EntityMapper;

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

    @Override
    protected EntityRepository<Long, StockDO> getRepository() {
        return stockRepository;
    }

    @Override
    protected EntityMapper<StockDTO, StockDO> getMapper() {
        return stockMapper;
    }
}
