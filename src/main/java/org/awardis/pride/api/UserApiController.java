package org.awardis.pride.api;

import com.dropbox.core.DbxException;
import lombok.extern.slf4j.Slf4j;
import org.awardis.pride.dto.User;
import org.awardis.pride.service.SecurityService;
import org.awardis.pride.service.UserService;
import org.awardis.pride.util.Response;
import org.awardis.pride.vo.UserEditVO;
import org.awardis.pride.vo.UserProfileVO;
import org.awardis.pride.vo.UserRegistrationVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @PostMapping
    public ResponseEntity<Response> createUser(@Valid @RequestBody UserRegistrationVO user, UriComponentsBuilder builder) {

        log.debug("Creating user: {}", user);

        Long id = userService.createUser(modelMapper.map(user, User.class));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/api/user/{id}").buildAndExpand(id).toUri());

        return new ResponseEntity<>(new Response(null), headers, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getUser(@PathVariable("id") Long id) {

        log.debug("Fetching user with id {}", id);
        UserProfileVO user = modelMapper.map(userService.getUser(id), UserProfileVO.class);

        return new ResponseEntity<>(new Response(user), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Response> getUserListPage(@RequestParam(value = "page", required = false) Integer page,
                                             @RequestParam(value = "size", required = false) Integer size) {

        log.debug("Fetching user list");
        Page<User> users = userService.getUserListPage(page, size);

        if (users == null) {
            return new ResponseEntity<>(new Response(null), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(new Response(users.map(user -> modelMapper.map(user, UserProfileVO.class))), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> editUser(@PathVariable("id") Long id, @Valid @RequestBody UserEditVO userEditVO) {
        log.debug("Updating user with id {}", id);
        User user = modelMapper.map(userEditVO, User.class);
        UserProfileVO updatedUser =
                modelMapper.map(userService.editUser(id, user, securityService.getPrincipalId()), UserProfileVO.class);

        return new ResponseEntity<>(new Response(updatedUser), HttpStatus.OK);
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<Response> disableUser(@PathVariable("id") Long id) {

        log.debug("Disabling user with id {}", id);
        UserProfileVO disabledUser =
                modelMapper.map(userService.disableUser(id, securityService.getPrincipalId()), UserProfileVO.class);

        return new ResponseEntity<>(new Response(disabledUser), HttpStatus.OK);
    }

    @PutMapping("/enable/{id}")
    public ResponseEntity<Response> enableUser(@PathVariable("id") Long id) {

        log.debug("Enabling user with id {}", id);
        UserProfileVO enabledUser =
                modelMapper.map(userService.enableUser(id, securityService.getPrincipalId()), UserProfileVO.class);

        return new ResponseEntity<>(new Response(enabledUser), HttpStatus.OK);
    }

    @PostMapping("/{id}/upload")
    public ResponseEntity<Response> uploadUserAvatar(@PathVariable("id") Long id, @RequestParam("avatar") MultipartFile avatarFile)
            throws IOException, DbxException {

        log.debug("Uploading avatar for user with id {}", id);
        userService.uploadUserAvatar(id, avatarFile, securityService.getPrincipalId());

        return new ResponseEntity<>(new Response(null), HttpStatus.OK);
    }
}
