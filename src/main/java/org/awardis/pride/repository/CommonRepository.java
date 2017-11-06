package org.awardis.pride.repository;

import org.awardis.pride.model.CommonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonRepository<T extends CommonEntity> extends JpaRepository<T, Long> {
}
