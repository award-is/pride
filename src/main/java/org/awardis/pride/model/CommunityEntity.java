package org.awardis.pride.model;

import lombok.*;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "community")
public class CommunityEntity extends CommonEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "avatar")
    private String avatarURL;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    private AccessPermission accessPermission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private UserEntity admin;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "userCommunity",
            joinColumns = @JoinColumn(name = "email"),
            inverseJoinColumns = @JoinColumn(name = "name"))
    private List<UserEntity> participants;

    @OneToMany(targetEntity = AchievementLayerEntity.class, mappedBy = "name")
    private List<AchievementLayerEntity> achievementLayer;
}