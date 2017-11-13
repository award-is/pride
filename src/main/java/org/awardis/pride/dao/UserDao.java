package org.awardis.pride.dao;

import org.awardis.pride.dto.User;

public interface UserDao extends CommonDao<User> {
    boolean isEmailUsed(String email);
    User findByEmail(String email);
}
