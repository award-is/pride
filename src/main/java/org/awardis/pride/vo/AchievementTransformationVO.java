package org.awardis.pride.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AchievementTransformationVO extends CommonVO {
    private Integer countOfSourceAchievements;
    private AchievementTypeVO achievementType;
}