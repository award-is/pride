package org.awardis.pride.dto;

import lombok.Getter;
import lombok.Setter;
import org.awardis.pride.model.VisibilityType;

import java.util.List;

@Getter
@Setter
public class Achievement extends Common {
    private List<AchievementType> achievementTypes;
    private List<User> users;
    private VisibilityType visibilityType;
}