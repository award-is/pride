package org.awardis.pride.service;

import org.awardis.pride.dao.UserDao;
import org.awardis.pride.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Override
    public Long createUser(User user) {
        if (userDao.isEmailUsed(user.getEmail())) {
            throw new EntityExistsException(
                    String.format("The user with email '%s' is already exist.", user.getEmail()));
        }
        user.setEmail(user.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user).getId();
    }
}
