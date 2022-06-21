package com.ivscheianu.stockmanagement.image;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivscheianu.base.service.AbstractDTO;
import com.ivscheianu.stockmanagement.bucket.BucketDTO;
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
@ToString(callSuper = true, exclude = "bucket")
@EqualsAndHashCode(callSuper = true, exclude = "bucket")
public class ImageDTO extends AbstractDTO<Long> {

    private Long id;

    private Long productId;

    private Integer storageType;

    private String location;

    @JsonIgnore
    private BucketDTO bucket;
}
