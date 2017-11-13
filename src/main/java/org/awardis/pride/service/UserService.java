package org.awardis.pride.service;

import org.awardis.pride.dto.User;

public interface UserService {
    /**
     * Creates a new user.
     *
     * @param user data for new user.
     * @return id of created user.
     */
    Long createUser(User user);
}
