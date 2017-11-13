package org.awardis.pride.service;

import com.dropbox.core.DbxException;
import org.awardis.pride.dao.UserDao;
import org.awardis.pride.dto.User;
import org.awardis.pride.cloud.CloudStorageClient;
import org.awardis.pride.util.AccountStatus;
import org.awardis.pride.util.Inspector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Locale;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CloudStorageClient cloudStorageClient;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserDao userDao;

    @Override
    public Long createUser(User user) {
        if (userDao.isEmailUsed(user.getEmail())) {
            String message = messageSource.getMessage("error.email.exist", new Object[] {user.getEmail()}, Locale.ENGLISH);
            throw new EntityExistsException(message);
        }
        User userToSave = new User();
        userToSave.setEmail(user.getEmail().toLowerCase());
        userToSave.setPassword(passwordEncoder.encode(user.getPassword()));
        userToSave.setStatus(AccountStatus.UNCONFIRMED);

        return userDao.save(userToSave).getId();
    }

    @Override
    public User getUser(Long id) {
        return userDao.find(id);
    }

    @Override
    public Page<User> getUserListPage(Integer page, Integer size) {
        if (!Inspector.isValidPageSizePair(page, size)) {
            String message = messageSource.getMessage("error.parameter_set.invalid", null, Locale.ENGLISH);
            throw new InvalidParameterException(message);
        }

        Integer p = page;
        Integer s = size;
        if (p == null && s == null) {
            p = 1;
            s = userDao.count().intValue();
            if (s == 0) {
                return null;
            }
        }
        Pageable pageable = new PageRequest(p - 1, s);

        return userDao.findAll(pageable);
    }

    @Override
    public User editUser(Long id, User user, Long principalId) {
        Inspector.checkUserAccessPermission(id, principalId);
        User userToUpdate = getUser(id);
        Inspector.checkAccountActivation(userToUpdate.getStatus());
        userToUpdate.setNickname(user.getNickname());

        return userDao.save(userToUpdate);
    }

    @Override
    public User disableUser(Long id, Long principalId) {
        Inspector.checkUserAccessPermission(id, principalId);
        User userToDisable = getUser(id);
        Inspector.checkAccountActivation(userToDisable.getStatus());
        userToDisable.setStatus(AccountStatus.DISABLED);

        return userDao.save(userToDisable);
    }

    @Override
    public User enableUser(Long id, Long principalId) {
        Inspector.checkUserAccessPermission(id, principalId);
        User userToEnable = getUser(id);
        Inspector.checkAccountActivation(userToEnable.getStatus());
        userToEnable.setStatus(AccountStatus.ENABLED);

        return userDao.save(userToEnable);
    }

    @Override
    public void uploadUserAvatar(Long id, MultipartFile avatarFile, Long principalId) throws IOException, DbxException {
        Inspector.checkUserAccessPermission(id, principalId);
        if (!Inspector.isImageFile(avatarFile.getOriginalFilename())) {
            String message = messageSource.getMessage("error.file.extension.not_support", null, Locale.ENGLISH);
            throw new InvalidParameterException(message);
        }
        User user = userDao.find(id);
        //Inspector.checkAccountActivation(user.getStatus());
        String pathToSave = String.format("/user/%d/avatar.jpg", id);
        cloudStorageClient.uploadFile(avatarFile.getInputStream(), pathToSave);
        String sharedUrl = cloudStorageClient.createSharedLink(pathToSave);
        user.setAvatarUrl(sharedUrl);
        userDao.save(user);
    }
}
