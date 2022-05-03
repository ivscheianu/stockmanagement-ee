package com.ivscheianu.base;

import java.io.Serializable;

public interface HasId<E extends Serializable> {
    E getId();
}
