package org.awardis.pride.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "community")
public class CommunityEntity extends CommonEntity {
    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    List<AchievementLayerEntity> achievementLayers;
}
