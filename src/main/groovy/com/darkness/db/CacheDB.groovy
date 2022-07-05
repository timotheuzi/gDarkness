package com.darkness.db

import lombok.Data
import lombok.Getter
import lombok.Setter

import javax.persistence.*

//item table
@Entity
@Getter
@Setter
@Table(name = "cache")
class CacheDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id
    Integer mapId
    String usersInRoom
    String npcsInRoom
    String itemsInRoom
    String msg

}