package com.darkness.db

import lombok.Getter
import lombok.Setter

import javax.persistence.*

//item table

@Getter
@Setter
@Entity
@Table(name = "msg_cache")
class CacheDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id
    private String mapName
    private String currentRoomStatus

}