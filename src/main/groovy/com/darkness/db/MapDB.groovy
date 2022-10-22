package com.darkness.db


import lombok.Getter
import lombok.Setter

import javax.persistence.*

@Entity
@Getter
@Setter
@Table(name = "map")
class MapDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id
    String name
    String description
}