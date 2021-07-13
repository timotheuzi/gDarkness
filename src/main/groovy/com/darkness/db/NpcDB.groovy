package com.darkness.db

import lombok.Getter
import lombok.Setter

import javax.persistence.*

//for NPC generation

@Getter
@Setter
@Entity
@Table(name = "npc")
class NpcDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id

    private String name
    private String description
    private Integer attack
    private Integer defense
    private Integer location
    private Integer hp

}