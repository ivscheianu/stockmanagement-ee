package com.ivscheianu.stockmanagement.user;

import com.ivscheianu.base.service.AbstractDTO;
import com.ivscheianu.stockmanagement.bucket.BucketDTO;
import com.ivscheianu.stockmanagement.role.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

import javax.json.bind.annotation.JsonbTypeSerializer;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@JsonbTypeSerializer(value = UserDTOSerializer.class)
public class UserDTO extends AbstractDTO<Long> {

    private Long id;

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Set<RoleDTO> roles;

    private BucketDTO bucket;
}
