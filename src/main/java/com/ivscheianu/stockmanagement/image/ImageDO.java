package com.ivscheianu.stockmanagement.image;

import com.ivscheianu.base.persistence.AbstractDO;
import com.ivscheianu.stockmanagement.bucket.BucketDO;
import com.ivscheianu.stockmanagement.product.ProductDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@NaturalIdCache
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
@Builder(toBuilder = true)
@ToString(callSuper = true, exclude = {"bucket", "product"})
@EqualsAndHashCode(callSuper = true, exclude = {"bucket", "product"})
public class ImageDO extends AbstractDO<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductDO product;

    @Column(nullable = false, name = "storage_type")
    private Integer storageType;

    @Column(nullable = false)
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    private BucketDO bucket;
}
