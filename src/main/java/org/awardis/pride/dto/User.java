package org.awardis.pride.dto;

import lombok.Getter;
import lombok.Setter;
import org.awardis.pride.util.AccountStatus;

@Getter
@Setter
public class User extends Common {
    private String email;
    private String password;
    private AccountStatus status;
    private String nickname;
    private String avatarUrl;
}
