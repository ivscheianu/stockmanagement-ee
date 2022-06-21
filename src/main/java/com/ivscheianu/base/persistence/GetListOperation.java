package com.ivscheianu.base.persistence;

import java.io.Serializable;
import java.util.List;

@FunctionalInterface
public interface GetListOperation<DoType extends AbstractDO<? extends Serializable>> {
    List<DoType> execute();
}
