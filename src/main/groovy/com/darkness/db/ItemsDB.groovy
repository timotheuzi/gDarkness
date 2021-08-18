package com.darkness.db

import lombok.Getter
import lombok.Setter

import javax.persistence.*

//item table


@Entity
@Table(name = "items")
@Getter
@Setter
class ItemsDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id
    public String itemName
    public String itemDescription
    public Integer itemAttack
    public Integer itemDefense
    public Integer itemLocation
}