package com.ivscheianu.stockmanagement.product;

import com.ivscheianu.base.controller.AbstractEntityController;
import com.ivscheianu.base.service.EntityService;
import com.ivscheianu.stockmanagement.role.RoleEnum;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("products")
public class ProductController extends AbstractEntityController<Long, ProductDTO> {

    @Inject
    private ProductService productService;

    @Override
    protected EntityService<Long, ProductDTO> getService() {
        return productService;
    }

    @GET
    @Path("/barcode/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({RoleEnum.Constants.ROLE_USER})
    public ProductDTO getByBarcode(@PathParam("barcode") final String barcode) {
        return productService.getByBarcode(barcode);
    }
}
