package com.ivscheianu.stockmanagement.product;

import static com.querydsl.core.types.ExpressionUtils.allOf;

import com.ivscheianu.base.persistence.AbstractQueryDslRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;

import java.util.Optional;

import javax.ejb.Stateless;

@Stateless
public class ProductRepositoryImpl extends AbstractQueryDslRepository<Long, ProductDO> implements ProductRepository {

    private static final String BARCODE = "barcode";

    private static final QProductDO PRODUCT_MODEL = QProductDO.productDO;

    @Override
    public Optional<ProductDO> getByBarcode(final String barcode, final long userId) {
        return execute(() -> {
            final Predicate barcodeEquals = PRODUCT_MODEL.barcode.eq(barcode);
            final Predicate userEquals = PRODUCT_MODEL.user.id.eq(userId);
            final JPAQuery<ProductDO> query = newJpaQuery()
                .select(PRODUCT_MODEL)
                .from(PRODUCT_MODEL)
                .where(allOf(barcodeEquals, userEquals));
            return Optional.of(query.fetchFirst());
        });
    }

    @Override
    protected EntityPathBase<ProductDO> getEntityModel() {
        return PRODUCT_MODEL;
    }
}
