package com.darkness.db

import lombok.Data
import lombok.Getter
import lombok.Setter

import javax.persistence.*

//for NPC generation
@Entity
@Getter
@Setter
@Table(name = "npc")
class NpcDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id
    String name
    String description
    Integer attack
    Integer defense
    Integer location
    Integer hp
}