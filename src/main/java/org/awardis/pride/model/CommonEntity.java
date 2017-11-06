package org.awardis.pride.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * CommonEntity class is abstract class for all entities with common fields, such as id.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class CommonEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
