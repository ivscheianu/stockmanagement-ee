package com.ivscheianu.stockmanagement.auth.crypto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Encryption {

    B_CRYPT(1, "BCrypt", "{bcrypt}");

    private final int id;
    private final String name;
    private final String tag;
}
