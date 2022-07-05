package com.darkness.db

import lombok.Data
import lombok.Getter
import lombok.Setter

import javax.persistence.*

//item table
@Entity
@Getter
@Setter
@Table(name = "items")
class ItemsDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id
    String name
    String description
    Integer attack
    Integer defense
    Integer location
    Integer owner
    Integer value
}