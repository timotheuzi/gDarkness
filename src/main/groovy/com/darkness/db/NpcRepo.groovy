package com.darkness.db

import org.springframework.data.repository.CrudRepository

//import com.darkness.db.npcDB

interface NpcRepo extends CrudRepository<NpcDB, Integer> {
    NpcDB findByNameIgnoreCase(String npcName)
    //NpcDB findByNpcLocation(Integer location)
    NpcDB findByLocation(Integer npcLocation)
}