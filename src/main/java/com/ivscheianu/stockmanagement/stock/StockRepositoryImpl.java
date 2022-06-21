package com.ivscheianu.stockmanagement.stock;

import com.ivscheianu.base.persistence.AbstractEntityRepository;

import javax.ejb.Stateless;

@Stateless
public class StockRepositoryImpl extends AbstractEntityRepository<Long, StockDO> implements StockRepository {

}
