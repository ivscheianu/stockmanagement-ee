package com.ivscheianu.stockmanagement.product;

import com.ivscheianu.base.service.AbstractDTO;
import com.ivscheianu.stockmanagement.image.ImageDTO;
import com.ivscheianu.stockmanagement.stock.StockDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDTO extends AbstractDTO<Long> {

    private String name;

    private String barcode;

    private List<StockDTO> stocks;

    private List<ImageDTO> images;
}
