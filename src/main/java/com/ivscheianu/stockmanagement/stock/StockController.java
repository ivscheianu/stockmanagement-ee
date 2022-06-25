package com.ivscheianu.stockmanagement.stock;

import com.ivscheianu.base.controller.AbstractEntityController;
import com.ivscheianu.base.service.EntityService;
import com.ivscheianu.stockmanagement.role.RoleEnum;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("stocks")
public class StockController extends AbstractEntityController<Long, StockDTO> {

    @Inject
    private StockService stockService;

    @POST
    @Path("/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({RoleEnum.Constants.ROLE_USER})
    public StockDTO addStockForProduct(@PathParam("barcode") final String barcode, final StockDTO stock) {
        return stockService.addStockForProduct(stock, barcode);
    }

    @Override
    public StockDTO save(final StockDTO dataTransferObject) {
        throw new WebApplicationException("The stock needs to be assigned to a product", Response.Status.BAD_REQUEST);
    }

    @Override
    protected EntityService<Long, StockDTO> getService() {
        return stockService;
    }
}
