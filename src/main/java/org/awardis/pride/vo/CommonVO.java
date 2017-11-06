package org.awardis.pride.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class CommonVO implements Serializable {
    private Long id;
}