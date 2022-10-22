package com.darkness.model

import lombok.Getter
import lombok.Setter

@Getter
@Setter
class ErrorResponse implements Serializable {
    String code
    String message
}
