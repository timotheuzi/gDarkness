package com.darkness.db

import lombok.Data
import lombok.Getter
import lombok.Setter

import javax.persistence.*

//for NPC generation
@Entity
@Data
@Table(name = "npc")
class NpcDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id
    public String name
    public String description
    public Integer attack
    public Integer defense
    public Integer location
    public Integer hp
}