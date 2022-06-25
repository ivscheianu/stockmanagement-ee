package com.ivscheianu.stockmanagement.stock;

import com.ivscheianu.base.persistence.AbstractQueryDslRepository;
import com.querydsl.core.types.dsl.EntityPathBase;

import javax.ejb.Stateless;

@Stateless
public class StockRepositoryImpl extends AbstractQueryDslRepository<Long, StockDO> implements StockRepository {
    @Override
    protected EntityPathBase<StockDO> getEntityModel() {
        return QStockDO.stockDO;
    }
}
