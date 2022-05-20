package com.darkness.db

import lombok.Data
import lombok.Setter
import lombok.Getter

import javax.persistence.*

@Entity
@Data
@Table(name = "map")
class MapDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id
    public String name
    public String description
}