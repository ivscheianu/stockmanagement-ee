package com.ivscheianu.base.persistence;

import com.ivscheianu.base.HasId;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class AbstractDO<IdType extends Serializable> implements HasId<IdType>, Serializable {

    @Getter
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @Getter
    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;

    @PrePersist
    protected void prePersist() {
        createdAt = ZonedDateTime.now(ZoneOffset.UTC);
        updatedAt = ZonedDateTime.now(ZoneOffset.UTC);
    }

    @PreUpdate
    protected void preUpdate() {
        updatedAt = ZonedDateTime.now(ZoneOffset.UTC);
    }
}
