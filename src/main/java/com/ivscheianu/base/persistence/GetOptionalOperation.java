package com.ivscheianu.base.persistence;

import java.io.Serializable;
import java.util.Optional;

@FunctionalInterface
public interface GetOptionalOperation<DoType extends AbstractDO<? extends Serializable>>  {
    Optional<DoType> execute();
}
