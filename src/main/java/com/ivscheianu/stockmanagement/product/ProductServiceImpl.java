package com.ivscheianu.stockmanagement.product;

import com.ivscheianu.base.persistence.EntityRepository;
import com.ivscheianu.base.service.AbstractEntityService;
import com.ivscheianu.base.service.EntityMapper;
import com.ivscheianu.stockmanagement.user.UserService;

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

    @Inject
    private UserService userService;

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
        final Optional<ProductDO> productDo = productRepository.getByBarcode(barcode, userService.getCurrentUser().getId());
        return mapToDto(productDo);
    }

    @Override
    public ProductDTO save(final ProductDTO product) {
        doBidirectionalLinking(product);
        return super.save(product);
    }

    @Override
    public ProductDTO update(final ProductDTO newVersion) {
        doBidirectionalLinking(newVersion);
        return super.update(newVersion);
    }

    private void doBidirectionalLinking(final ProductDTO product) {
        product.getStocks().forEach(stock -> stock.setProduct(product));
        product.getImages().forEach(image -> image.setProduct(product));
    }
}
