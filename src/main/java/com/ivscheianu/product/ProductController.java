package com.ivscheianu.product;

import com.ivscheianu.base.controller.AbstractEntityController;
import com.ivscheianu.base.service.EntityService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Stateless
@Path("product")
public class ProductController extends AbstractEntityController<Long, ProductDto> {

    @Inject
    private ProductService productService;

    @Override
    protected EntityService<Long, ProductDto> getService() {
        return productService;
    }

    @GET
    @Path("/barcode/{barcode}")
    @Produces("application/json")
    public ProductDto getByBarcode(@PathParam("barcode") final String barcode) {
        return productService.getByBarcode(barcode);
    }
}
