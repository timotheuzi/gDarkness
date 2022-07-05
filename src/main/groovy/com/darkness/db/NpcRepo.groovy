package com.darkness.db

import org.springframework.data.repository.CrudRepository

//import com.darkness.db.npcDB

interface NpcRepo extends CrudRepository<NpcDB, Integer> {
	Optional<NpcDB> findByName(String npcName)
	//NpcDB findByNpcLocation(Integer location)
	Optional<NpcDB> findByLocation(Integer npcLocation)
}