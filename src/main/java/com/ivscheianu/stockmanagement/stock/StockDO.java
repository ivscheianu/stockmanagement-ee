package com.ivscheianu.stockmanagement.stock;

import com.ivscheianu.base.persistence.AbstractDO;
import com.ivscheianu.stockmanagement.product.ProductDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock")
@Builder(toBuilder = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StockDO extends AbstractDO<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String unit;

    @Column(name = "alert_limit")
    private Integer alertLimit;

    @OneToOne(mappedBy = "stock", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private ProductDO product;
}
