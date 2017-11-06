package org.awardis.pride.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Community extends Common {
    List<AchievementLayer> achievementLayers;
}
