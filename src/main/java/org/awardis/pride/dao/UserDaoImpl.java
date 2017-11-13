package org.awardis.pride.dao;

import org.awardis.pride.dto.User;
import org.awardis.pride.model.UserEntity;
import org.awardis.pride.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class UserDaoImpl implements UserDao {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isEmailUsed(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public User findByEmail(String email) {
        UserEntity entity = userRepository.findByEmail(email);
        return (entity == null) ? null : modelMapper.map(entity, User.class);
    }

    @Override
    public User save(User user) {
        UserEntity entity = modelMapper.map(user, UserEntity.class);
        UserEntity savedEntity = userRepository.save(entity);
        return modelMapper.map(savedEntity, User.class);
    }

    @Override
    public User find(Long id) {
        UserEntity entity = findUser(id);
        return modelMapper.map(entity, User.class);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        List<User> users = new ArrayList<>();
        Page<UserEntity> userPage = userRepository.findAll(pageable);
        userPage.getContent().forEach(user -> users.add(modelMapper.map(user, User.class)));
        return new PageImpl<>(users, pageable, userPage.getTotalElements());
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll().stream()
                .map(userEntity -> modelMapper.map(userEntity, User.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public Long count() {
        return userRepository.count();
    }

    @Transactional(readOnly = true)
    private UserEntity findUser(Long id) {
        UserEntity entity = userRepository.findOne(id);
        if (entity == null) {
            String message = messageSource.getMessage("error.entity.not_found", new Object[]{"User", id}, Locale.ENGLISH);
            throw new EntityNotFoundException(message);
        }

        return entity;
    }
}
