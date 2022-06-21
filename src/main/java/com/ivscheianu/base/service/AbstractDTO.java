package com.ivscheianu.base.service;

import com.ivscheianu.base.HasId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class AbstractDTO<IdType extends Serializable> implements Serializable, HasId<IdType> {
    private IdType id;
}
