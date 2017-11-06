package org.awardis.pride.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "achievement_layer")
public class AchievementLayerEntity extends CommonEntity {
    @OneToMany(mappedBy = "achievement_layer", cascade = CascadeType.ALL)
    private List<AchievementTypeEntity> achievementTypes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community", nullable = false)
    private CommunityEntity community;
}
