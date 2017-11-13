package org.awardis.pride.dao;

import org.awardis.pride.dto.User;
import org.awardis.pride.model.UserEntity;
import org.awardis.pride.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserDaoImpl implements UserDao {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean isEmailUsed(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    @Transactional(readOnly = true)
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
}
