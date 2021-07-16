package com.darkness.db

import org.springframework.data.repository.CrudRepository

//import com.darkness.db.npcDB

interface NpcRepo extends CrudRepository<NpcDB, Integer> {
	NpcDB findByNpcName(String npcName)
	//NpcDB findByNpcLocation(Integer location)
	NpcDB findByNpcLocation(Integer npcLocation)
}