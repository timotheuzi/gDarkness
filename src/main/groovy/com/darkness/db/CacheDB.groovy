package com.darkness.db

import lombok.Getter
import lombok.Setter

import javax.persistence.*

//item table

@Entity
@Table(name = "msg_cache")
@Getter
@Setter
class CacheDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id
    public String cacheMapName
    public String cacheCurrentRoomStatus

}