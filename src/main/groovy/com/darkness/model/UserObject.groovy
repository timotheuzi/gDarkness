package com.darkness.model

import lombok.Getter
import lombok.Setter
import org.springframework.web.bind.annotation.RequestParam

@Getter
@Setter
class UserObject {
    String name
    Integer lvl
    Integer money
    Integer exp
    Integer attack
    Integer defense
    String description
    Integer location
    Integer hp
}
