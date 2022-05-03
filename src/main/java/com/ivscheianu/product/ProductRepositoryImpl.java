package com.ivscheianu.product;

import com.ivscheianu.base.repository.AbstractEntityRepository;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

@Stateless
public class ProductRepositoryImpl extends AbstractEntityRepository<Long, ProductDo> implements ProductRepository {

    private static final String BARCODE = "barcode";

    @Override
    public ProductDo getByBarcode(final String barcode) {
        final Predicate isEqual = criteriaBuilder.equal(root.get(BARCODE), barcode);
        final CriteriaQuery<ProductDo> query = criteriaQuery.select(root).where(isEqual);
        return entityManager.createQuery(query).getSingleResult();
    }
}
