package com.darkness.db

import lombok.Data
import lombok.Getter
import lombok.Setter

import javax.persistence.*

//item table
@Entity
@Data
@Table(name = "cache")
class CacheDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id
    public Integer mapId
    public String usersInRoom
    public String npcsInRoom
    public String itemsInRoom
    public String msg

}