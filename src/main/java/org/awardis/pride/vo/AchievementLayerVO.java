package org.awardis.pride.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AchievementLayerVO extends CommonVO {
    private List<AchievementTypeVO> achievementTypes;
    private CommunityVO community;
}
