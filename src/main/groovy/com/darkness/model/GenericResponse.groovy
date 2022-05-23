package com.darkness.model

import lombok.Getter
import lombok.Setter

@Getter
@Setter
class GenericResponse implements Serializable {
    String code
    String message
    Integer mapIndex
    String mapDescription
    List<String> npcs
    List<String> users
    String items
    UserObject userObj
}
