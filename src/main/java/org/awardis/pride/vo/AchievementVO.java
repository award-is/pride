package org.awardis.pride.vo;

import lombok.Getter;
import lombok.Setter;
import org.awardis.pride.model.VisibilityType;

import java.util.List;

@Getter
@Setter
public class AchievementVO extends CommonVO {
    private List<AchievementTypeVO> achievementTypes;
    private List<UserVO> users;
    private VisibilityType visibilityType;
}