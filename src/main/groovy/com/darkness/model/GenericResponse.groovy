package com.darkness.model

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Getter
import lombok.Setter

@Getter
@Setter
class GenericResponse implements Serializable {
    @JsonProperty("code")
    private String code

    @JsonProperty("message")
    private String message

}
