package org.awardis.pride.vo;

import lombok.Getter;
import lombok.Setter;
import org.awardis.pride.util.AccountStatus;

@Getter
@Setter
public class UserProfileVO extends CommonVO {
    private String email;
    private AccountStatus status;
    private String nickname;
    private String avatarUrl;
}
