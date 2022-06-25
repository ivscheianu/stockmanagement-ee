package com.ivscheianu.stockmanagement.product;

import com.ivscheianu.base.persistence.AbstractDO;
import com.ivscheianu.stockmanagement.image.ImageDO;
import com.ivscheianu.stockmanagement.stock.StockDO;
import com.ivscheianu.stockmanagement.user.UserDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Data
@Entity
@NaturalIdCache
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@Builder(toBuilder = true)
@ToString(callSuper = true, exclude = {"user"})
@EqualsAndHashCode(callSuper = true, exclude = {"user"})
public class ProductDO extends AbstractDO<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(nullable = false, unique = true)
    private String barcode;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserDO user;

    @OneToMany(
        mappedBy = "product",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<StockDO> stocks;

    @OneToMany(
        mappedBy = "product",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ImageDO> images;
}
