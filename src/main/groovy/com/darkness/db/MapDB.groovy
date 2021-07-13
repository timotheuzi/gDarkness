package com.darkness.db

import jdk.nashorn.internal.objects.annotations.Getter
import lombok.Setter
import lombok.Getter

import javax.persistence.*

//map tables

@Getter
@Setter
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