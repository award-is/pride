package org.awardis.pride.service;

import com.dropbox.core.DbxException;
import org.awardis.pride.dto.User;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    Long createUser(User user);

    User getUser(Long id);

    Page<User> getUserListPage(Integer page, Integer size);

    User editUser(Long id, User user, Long principalId);

    User disableUser(Long id, Long principalId);

    User enableUser(Long id, Long principalId);

    void uploadUserAvatar(Long id, MultipartFile avatarFile, Long principalId) throws IOException, DbxException;
}
