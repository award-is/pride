package org.awardis.pride.persistence.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_profile")
public class UserProfileEntity extends CommonEntity {
    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(name = "avatar")
    private String avatarUrl;
}
