package com.ivscheianu.stockmanagement.stock;

import com.ivscheianu.base.persistence.AbstractDO;
import com.ivscheianu.stockmanagement.product.ProductDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock")
@Builder(toBuilder = true)
@ToString(callSuper = true, exclude = {"product"})
@EqualsAndHashCode(callSuper = true, exclude = {"product"})
public class StockDO extends AbstractDO<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String unit;

    private String location;

    @Column(name = "alert_limit")
    private Integer alertLimit;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductDO product;
}
