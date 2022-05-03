package com.ivscheianu.base.service;

import com.ivscheianu.base.repository.AbstractDo;
import com.ivscheianu.base.repository.EntityRepository;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

@Slf4j
public abstract class AbstractEntityService<IdType extends Serializable, DtoType extends AbstractDto<IdType>, DoType extends AbstractDo<IdType>> implements EntityService<IdType, DtoType> {

    protected abstract EntityRepository<IdType, DoType> getRepository();
    protected abstract EntityMapper<DtoType, DoType> getMapper();

    @Override
    public DtoType save(final DtoType dataTransferObject) {
        final DoType toBeSaved = getMapper().toDo(dataTransferObject);
        final DoType saved = getRepository().save(toBeSaved);
        return getMapper().toDto(saved);
    }

    @Override
    public DtoType get(final IdType id) {
        final DoType databaseObject = getRepository().get(id);
        return getMapper().toDto(databaseObject);
    }

    @Override
    public List<DtoType> getAll() {
        final List<DoType> allDtos = getRepository().getAll();
        return getMapper().toDto(allDtos);
    }

    @Override
    public DtoType update(final DtoType dataTransferObject) {
        final DoType updatedVersion = getMapper().toDo(dataTransferObject);
        final DoType newVersion = getRepository().update(updatedVersion);
        return getMapper().toDto(newVersion);
    }

    @Override
    public void delete(final IdType id) {
        getRepository().delete(id);
    }
}
