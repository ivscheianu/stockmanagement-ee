package com.ivscheianu.product;

import com.ivscheianu.base.repository.EntityRepository;
import com.ivscheianu.base.service.AbstractEntityService;
import com.ivscheianu.base.service.EntityMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ProductServiceImpl extends AbstractEntityService<Long, ProductDto, ProductDo> implements ProductService {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ProductMapper productMapper;

    @Override
    protected EntityRepository<Long, ProductDo> getRepository() {
        return productRepository;
    }

    @Override
    protected EntityMapper<ProductDto, ProductDo> getMapper() {
        return productMapper;
    }

    @Override
    public ProductDto getByBarcode(final String barcode) {
        final ProductDo productDo = productRepository.getByBarcode(barcode);
        return productMapper.toDto(productDo);
    }
}
