package org.awardis.pride.dao;

import org.springframework.transaction.annotation.Transactional;

public interface CommonDao<T> {
    @Transactional
    T save(T object);
}
