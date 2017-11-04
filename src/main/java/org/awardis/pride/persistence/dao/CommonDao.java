package org.awardis.pride.persistence.dao;

import org.awardis.pride.persistence.model.CommonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonDao<T extends CommonEntity> extends JpaRepository<T, Long> {
}
