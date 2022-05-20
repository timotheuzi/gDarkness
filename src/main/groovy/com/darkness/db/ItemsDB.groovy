package com.darkness.db

import lombok.Data
import lombok.Getter
import lombok.Setter

import javax.persistence.*

//item table
@Entity
@Data
@Table(name = "items")
class ItemsDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id
    public String name
    public String description
    public Integer attack
    public Integer defense
    public Integer location
    public Integer owner
    public Integer value
}