package org.awardis.pride.vo;

import lombok.Getter;
import lombok.Setter;
import org.awardis.pride.model.AssignmentRule;

import java.util.Set;

@Getter
@Setter
public class AchievementTypeVO extends CommonVO {
    private String name;
    private String image;
    private String description;
    private Set<AssignmentRule> assignmentRules;
    private AchievementLayerVO achievementLayer;
    private AchievementVO achievement;
    private AchievementTransformationVO achievementTransformation;
}
