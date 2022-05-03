package com.ivscheianu.product;

import com.ivscheianu.base.service.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDto extends AbstractDto<Long> {
    private String name;
    private String barcode;
}
