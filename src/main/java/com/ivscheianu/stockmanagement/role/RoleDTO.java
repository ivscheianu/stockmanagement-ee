package com.ivscheianu.stockmanagement.role;

import com.ivscheianu.base.service.AbstractDTO;
import com.ivscheianu.stockmanagement.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

import javax.json.bind.annotation.JsonbTransient;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(callSuper = true, exclude = "users")
@EqualsAndHashCode(callSuper = true, exclude = "users")
public class RoleDTO extends AbstractDTO<Long> {

    private Long id;

    private String name;

    private String label;

    private String description;

    @JsonbTransient
    private Set<UserDTO> users;
}
