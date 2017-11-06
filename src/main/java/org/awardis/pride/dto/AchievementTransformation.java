package org.awardis.pride.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AchievementTransformation extends Common {
    @NotBlank
    @Min(2)
    @Max(10)
    private Integer countOfSourceAchievements;
    @NotBlank
    private AchievementType achievementType;
}