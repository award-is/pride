package org.awardis.pride.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommunityVO extends CommonVO {
    List<AchievementLayerVO> achievementLayers;
}
