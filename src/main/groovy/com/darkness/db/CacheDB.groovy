package com.darkness.db

import lombok.Getter
import lombok.Setter

import javax.persistence.*

//item table

@Entity
@Table(name = "cache")
@Getter
@Setter
class CacheDB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id
    public Integer mapId
    public List<Integer> usersInRoom
    public List<Integer> npcsInRoom
    public List<Integer> itemsInRoom

}