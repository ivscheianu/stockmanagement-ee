package com.ivscheianu.stockmanagement.stock;

import com.ivscheianu.base.controller.AbstractEntityController;
import com.ivscheianu.base.service.EntityService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

@Stateless
@Path("stocks")
public class StockController extends AbstractEntityController<Long, StockDTO> {

    @Inject
    private StockService stockService;

    @Override
    protected EntityService<Long, StockDTO> getService() {
        return stockService;
    }
}
