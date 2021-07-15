package com.darkness.db

import org.springframework.data.repository.CrudRepository

//import com.darkness.db.mapDB

interface MapRepo extends CrudRepository<MapDB, String> {
	MapDB findByMapName(String name)
	//MapDB findByID(String id)
	// Optional<mapDB> findById(Integer intValue)
}