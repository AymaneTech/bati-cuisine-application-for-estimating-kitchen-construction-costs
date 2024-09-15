package com.wora.common.infrastructure.persistence;

import java.sql.SQLException;

@FunctionalInterface
public interface SQLConsumer<T> {
    void run(T t) throws SQLException;
}
