package org.awardis.pride.dao;

import org.awardis.pride.dto.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao extends CommonDao<User> {
    @Transactional(readOnly = true)
    boolean isEmailUsed(String email);

    @Transactional(readOnly = true)
    User findByEmail(String email);
}
