package com.ivscheianu.product;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Stateless
@Path("product")
public class ProductResource {

    @Inject
    private ProductService productService;

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public ProductDto getById(@PathParam("id") final Long id) {
        return productService.get(id);
    }

    @GET
    @Path("/barcode/{barcode}")
    @Produces("application/json")
    public ProductDto getByBarcode(@PathParam("barcode") final String barcode) {
        return productService.getByBarcode(barcode);
    }

    @GET
    @Produces("application/json")
    public List<ProductDto> getById() {
        return productService.getAll();
    }

    @POST
    @Consumes("application/json")
    public void save(final ProductDto productDto) {
        productService.save(productDto);
    }

//    @PUT
//    @Consumes("application/json")
//    public void update(ProductDto ProductDto) {
//        ProductDtoDao.update(ProductDto);
//    }
//
//    @DELETE
//    @Path("/{id}")
//    @Consumes("application/json")
//    public void delete(@PathParam("id") Long id) {
//        ProductDto ProductDto = ProductDtoDao.find(id);
//        ProductDtoDao.delete(ProductDto);
//    }
}
