package com.ivscheianu.stockmanagement.product;

import com.ivscheianu.base.persistence.AbstractEntityRepository;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

@Stateless
public class ProductRepositoryImpl extends AbstractEntityRepository<Long, ProductDO> implements ProductRepository {

    private static final String BARCODE = "barcode";

    @Override
    public Optional<ProductDO> getByBarcode(final String barcode) {
        return execute(() -> {
            final Predicate isEqual = criteriaBuilder.equal(root.get(BARCODE), barcode);
            final CriteriaQuery<ProductDO> query = criteriaQuery.select(root).where(isEqual);
            final ProductDO result = entityManager.createQuery(query).getSingleResult();
            return Optional.of(result);
        });
    }
}
