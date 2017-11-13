package org.awardis.pride.service;

import org.awardis.pride.dao.UserDao;
import org.awardis.pride.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Locale;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email);
        if (user == null) {
            String message = messageSource.getMessage("error.email.not_found", new Object[] {email}, Locale.ENGLISH);
            throw new UsernameNotFoundException(message);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new HashSet<>());
    }
}
