package org.awardis.pride.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AchievementLayer extends Common {
    private List<AchievementType> achievementTypes;
    private Community community;
}
