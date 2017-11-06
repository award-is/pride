package org.awardis.pride.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "achievement")
public class AchievementEntity extends CommonEntity {
    @OneToMany(mappedBy = "achievement", cascade = CascadeType.ALL)
    private List<AchievementTypeEntity> achievementTypes;

    @OneToMany(mappedBy = "achievement", cascade = CascadeType.ALL)
    private List<UserEntity> users;

    @Enumerated(EnumType.STRING)
    private VisibilityType visibilityType;

    public AchievementEntity() {
        this.visibilityType = VisibilityType.ANYONE;
    }
}