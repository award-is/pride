package org.awardis.pride.dto;

import org.awardis.pride.model.AccessPermission;
import org.awardis.pride.model.AchievementLayerEntity;
import org.awardis.pride.model.UserEntity;

import java.util.List;

public class Community {
    private String name;
    private String avatarURL;
    private String description;
    private AccessPermission accessPermission;
    private UserEntity admin;
    private List<UserEntity> participants;
    private List<AchievementLayerEntity> achievementLayer;
}
