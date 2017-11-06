package org.awardis.pride.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "achievement_transformation")
public class AchievementTransformationEntity extends CommonEntity {
    @Column(name = "count")
    private Integer countOfSourceAchievements;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "achievement_type", nullable = false)
    private AchievementTypeEntity achievementType;
}