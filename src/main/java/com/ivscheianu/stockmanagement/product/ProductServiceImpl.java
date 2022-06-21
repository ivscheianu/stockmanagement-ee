package com.ivscheianu.stockmanagement.product;

import com.ivscheianu.base.persistence.EntityRepository;
import com.ivscheianu.base.service.AbstractEntityService;
import com.ivscheianu.base.service.EntityMapper;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Stateless
@Transactional
public class ProductServiceImpl extends AbstractEntityService<Long, ProductDTO, ProductDO> implements ProductService {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ProductMapper productMapper;

    @Override
    protected EntityRepository<Long, ProductDO> getRepository() {
        return productRepository;
    }

    @Override
    protected EntityMapper<ProductDTO, ProductDO> getMapper() {
        return productMapper;
    }

    @Override
    public ProductDTO getByBarcode(final String barcode) {
        final Optional<ProductDO> productDo = productRepository.getByBarcode(barcode);
        return mapToDto(productDo);
    }
}
