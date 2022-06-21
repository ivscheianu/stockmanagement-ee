package com.ivscheianu.stockmanagement.image;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StorageType {

    BUCKET(1),
    LINK(2);

    private final int id;
}
