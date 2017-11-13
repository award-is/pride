package org.awardis.pride.repository;

import org.awardis.pride.model.UserEntity;

public interface UserRepository extends CommonRepository<UserEntity> {
    UserEntity findByEmail(String email);
}
