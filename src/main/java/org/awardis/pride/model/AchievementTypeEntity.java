package org.awardis.pride.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "achievement_type")
public class AchievementTypeEntity extends CommonEntity {
    private String name;

    private String image;

    private String description;

    @Column(name = "assignment_rules")
    @Enumerated(EnumType.STRING)
    private Set<AssignmentRule> assignmentRules;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "achievement_layer", nullable = false)
    private AchievementLayerEntity achievementLayer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "achievement", nullable = false)
    private AchievementEntity achievement;

    @OneToOne(mappedBy = "achievement_type", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private AchievementTransformationEntity achievementTransformation;
}