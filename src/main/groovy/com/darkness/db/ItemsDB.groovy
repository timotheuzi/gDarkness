package com.darkness.db;

import javax.persistence.*;

//item table

@Entity
@Table(name = "items")
class ItemsDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private Integer attack;
    private Integer defense;

}