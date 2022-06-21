package com.ivscheianu.stockmanagement.stock;

import com.ivscheianu.base.service.AbstractDTO;
import com.ivscheianu.stockmanagement.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class StockDTO extends AbstractDTO<Long> {

    private Long id;

    private String name;

    private String description;

    private String unit;

    private Integer alertLimit;

    private ProductDTO product;
}
