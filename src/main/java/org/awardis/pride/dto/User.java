package org.awardis.pride.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends Common {
    private String email;
    private String password;
    private String nickname;
    private String avatarUrl;
}
