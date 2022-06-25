package com.ivscheianu.stockmanagement.image;

import com.ivscheianu.base.service.AbstractDTO;
import com.ivscheianu.stockmanagement.bucket.BucketDTO;
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
@ToString(callSuper = true, exclude = {"bucket", "product"})
@EqualsAndHashCode(callSuper = true, exclude = {"bucket", "product"})
public class ImageDTO extends AbstractDTO<Long> {

    private Long id;

    private Integer storageType;

    private String location;

    private String identifier;

    @JsonbTransient
    private ProductDTO product;

    @JsonbTransient
    private BucketDTO bucket;
}
