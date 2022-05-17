package com.darkness.db

import lombok.Setter
import lombok.Getter

import javax.persistence.*

@Entity
@Table(name = "map")
@Getter
@Setter
class MapDB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id
    public String mapName
    public String mapDescription
}