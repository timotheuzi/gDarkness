package com.darkness.db

import javax.persistence.*

//map tables

@Entity
@Table(name = "map")
class MapDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id

    private String name
    private String description
    private Integer items
    private Integer npcs
    private Integer users

}