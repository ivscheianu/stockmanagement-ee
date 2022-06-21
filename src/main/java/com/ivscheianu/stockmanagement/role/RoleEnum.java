package com.ivscheianu.stockmanagement.role;

import static com.ivscheianu.stockmanagement.role.RoleEnum.Constants.ROLE_ADMIN;
import static com.ivscheianu.stockmanagement.role.RoleEnum.Constants.ROLE_USER;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum RoleEnum {

    USER(1, "User", ROLE_USER),
    ADMIN(2, "Admin", ROLE_ADMIN);

    private final int id;
    private final String name;
    public final String label;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Constants {
        public static final String ROLE_USER = "ROLE_USER";
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
    }
}
