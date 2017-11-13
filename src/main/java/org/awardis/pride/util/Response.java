package org.awardis.pride.util;

import lombok.Getter;
import lombok.Setter;
import org.awardis.pride.error.CustomError;

import java.util.Set;

@Getter
@Setter
public class Response {
    private Object data;
    private Set<String> errors;

    public Response(Object obj) {
        if (obj instanceof CustomError) {
            this.errors = ((CustomError) obj).getErrors();
        } else {
            this.data = obj;
        }
    }
}
