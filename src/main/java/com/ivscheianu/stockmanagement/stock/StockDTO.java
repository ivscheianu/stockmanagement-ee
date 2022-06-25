package com.ivscheianu.stockmanagement.stock;

import com.ivscheianu.base.service.AbstractDTO;
import com.ivscheianu.stockmanagement.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.json.bind.annotation.JsonbTransient;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(callSuper = true, exclude = {"product"})
@EqualsAndHashCode(callSuper = true, exclude = {"product"})
public class StockDTO extends AbstractDTO<Long> {

    private Long id;

    private String name;

    private String description;

    private String unit;

    private String location;

    private Integer alertLimit;

    @JsonbTransient
    private ProductDTO product;
}
