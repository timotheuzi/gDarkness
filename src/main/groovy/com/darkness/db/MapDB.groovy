package com.darkness.db

import jdk.nashorn.internal.objects.annotations.Getter
import lombok.Setter
import lombok.Getter

import javax.persistence.*

//map tables
@Entity
@Table(name = "map")
@Getter
@Setter
class MapDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id
    public String mapName
    public String mapDescription
    public Integer mapItems
    public Integer mapNpcs
    public Integer mapUsers
}