package org.awardis.pride.dto;

import lombok.Getter;
import lombok.Setter;
import org.awardis.pride.model.AssignmentRule;

import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class AchievementType extends Common {
    @Size(max = 20)
    private String name;
    private String image;
    private String description;
    private Set<AssignmentRule> assignmentRules;
    private AchievementLayer achievementLayer;
    private Achievement achievement;
    private AchievementTransformation achievementTransformation;
}
