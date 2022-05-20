package com.darkness.db

import lombok.Data
import lombok.Getter
import lombok.Setter
import javax.persistence.*

//user objects
@Entity
@Data
@Table(name = "users")
class UserDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id
    public String name
    public Integer lvl
    public Integer money
    public Integer exp
    public Integer attack
    public Integer defense
    public String description
    public Integer location
    public Integer hp
    public List<String> items
}