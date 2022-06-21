package com.ivscheianu.stockmanagement.bucket;

import com.ivscheianu.base.service.AbstractDTO;
import com.ivscheianu.stockmanagement.image.ImageDTO;
import com.ivscheianu.stockmanagement.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import javax.json.bind.annotation.JsonbTransient;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(callSuper = true, exclude = "user")
@EqualsAndHashCode(callSuper = true, exclude = "user")
public class BucketDTO extends AbstractDTO<Long> {
    private Long id;

    private String name;

    private List<ImageDTO> images;

    @JsonbTransient
    private UserDTO user;
}
