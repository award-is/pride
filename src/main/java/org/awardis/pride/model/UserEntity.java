package org.awardis.pride.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.awardis.pride.util.AccountStatus;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity extends CommonEntity {
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private AccountStatus status;

    @Column(length = 20)
    private String nickname;

    @Column(name = "avatar")
    private String avatarUrl;
}
