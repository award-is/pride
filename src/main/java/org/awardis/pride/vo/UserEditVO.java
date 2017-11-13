package org.awardis.pride.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class UserEditVO {
    @Size(min = 4, max = 20, message = "Nickname must be at least then 4 symbols and no more then 20 symbols.")
    private String nickname;
}
