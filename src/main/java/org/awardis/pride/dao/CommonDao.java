package org.awardis.pride.dao;

import org.awardis.pride.dto.Common;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommonDao<T extends Common> {
    @Transactional
    T save(T object);

    @Transactional(readOnly = true)
    T find(Long id);

    @Transactional(readOnly = true)
    Page<T> findAll(Pageable pageable);

    @Transactional(readOnly = true)
    List<T> findAll();

    @Transactional
    void delete(Long id);

    @Transactional(readOnly = true)
    Long count();
}
