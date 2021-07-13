package com.darkness.db

import org.springframework.data.repository.CrudRepository

//import com.darkness.db.npcDB

interface NpcRepo extends CrudRepository<NpcDB, Integer> {
	NpcDB findByName(String name)
	NpcDB findByLocation(Integer location)
}