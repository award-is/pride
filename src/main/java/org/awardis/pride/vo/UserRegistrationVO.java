package org.awardis.pride.vo;

import cz.jirutka.validator.spring.SpELAssert;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.awardis.pride.util.Constants;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@SpELAssert(value = "password.equals(matchingPassword)", message = "Passwords are not the same")
public class UserRegistrationVO {
    @Pattern(regexp = Constants.EMAIL_REGEX, message = "Incorrect email format")
    private String email;

    @Pattern(regexp = Constants.PASSWORD_REGEX, message = "Incorrect password format")
    private String password;

    @Pattern(regexp = Constants.PASSWORD_REGEX, message = "Incorrect confirm password format")
    private String matchingPassword;
}
