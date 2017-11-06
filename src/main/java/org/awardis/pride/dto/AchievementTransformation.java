package org.awardis.pride.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class AchievementTransformation extends Common {
    @Min(2)
    @Max(4)
    private Integer countOfSourceAchievements;
    private AchievementType achievementType;
}