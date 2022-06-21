package com.ivscheianu.stockmanagement.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivscheianu.base.service.AbstractDTO;
import com.ivscheianu.stockmanagement.stock.StockDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(callSuper = true, exclude = "stock")
@EqualsAndHashCode(callSuper = true, exclude = "stock")
public class ProductDTO extends AbstractDTO<Long> {

    private String name;

    private String barcode;

    @JsonIgnore
    private StockDTO stock;
}
