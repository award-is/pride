package org.awardis.pride.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class CustomError {
    private Set<String> errors;

    public CustomError(String error) {
        errors = new HashSet<>();
        errors.add(error);
    }
}
