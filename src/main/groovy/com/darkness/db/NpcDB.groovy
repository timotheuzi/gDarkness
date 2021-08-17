package com.darkness.db

import lombok.Getter
import lombok.Setter

import javax.persistence.*

//for NPC generation
@Entity
@Table(name = "npc")
@Getter
@Setter
class NpcDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id
    public String npcName
    public String npcDescription
    public Integer npcAttack
    public Integer npcDefense
    public Integer npcLocation
    public Integer npcHp
}