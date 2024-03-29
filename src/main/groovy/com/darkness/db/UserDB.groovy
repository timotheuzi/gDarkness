package com.darkness.db


import lombok.Getter
import lombok.Setter

import javax.persistence.*

//user objects
@Entity
@Getter
@Setter
@Table(name = "users")
class UserDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id
    String name
    Integer lvl
    Integer money
    Integer exp
    Integer attack
    Integer defense
    String description
    Integer location
    Integer hp
    String items
}