package org.awardis.pride.api;

import lombok.extern.slf4j.Slf4j;
import org.awardis.pride.dto.User;
import org.awardis.pride.service.UserService;
import org.awardis.pride.util.Response;
import org.awardis.pride.vo.UserRegistrationVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Response> createUser(@Valid @RequestBody UserRegistrationVO user, UriComponentsBuilder builder) {
        log.debug("Creating user: {}", user);

        Long id = userService.createUser(modelMapper.map(user, User.class));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/api/user/{id}").buildAndExpand(id).toUri());

        return new ResponseEntity<>(new Response(null), headers, HttpStatus.CREATED);
    }
}
