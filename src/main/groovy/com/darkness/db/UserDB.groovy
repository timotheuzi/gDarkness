package com.darkness.db

import lombok.Getter
import lombok.Setter
import javax.persistence.*

//user objects
@Entity
@Table(name = "users")
@Getter
@Setter
class UserDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id
    public String userName
    public Integer userLvl
    public Integer userMoney
    public Integer userExp
    public Integer userAttack
    public Integer userDefense
    public String userDescription
    public Integer userLocation
    public Integer userHp
    public String userItems
}