package com.darkness.db

import javax.persistence.*

//item table

@Entity
@Table(name = "msg_cache")
class CacheDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id
    private String mapName
    private String currentRoomStatus

}