package com.wora.common.domain;

import java.time.LocalDateTime;
import java.util.Date;

public abstract class AbstractEntity<ID> {

    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected LocalDateTime deletedAt;

    public abstract ID id();

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public AbstractEntity<ID> setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }

    public AbstractEntity<ID> setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public LocalDateTime deletedAt() {
        return deletedAt;
    }

    public AbstractEntity<ID> setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }
}
