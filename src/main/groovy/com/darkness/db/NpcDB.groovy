package com.darkness.db

import javax.persistence.*

//for NPC generation

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