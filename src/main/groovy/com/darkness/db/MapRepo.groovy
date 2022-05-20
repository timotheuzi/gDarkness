package com.darkness.db

import org.springframework.data.repository.CrudRepository

//import com.darkness.db.mapDB

interface MapRepo extends CrudRepository<MapDB, String> {
	MapDB findByName(String name)
	MapDB findById(Integer id)
	// Optional<mapDB> findById(Integer intValue)
}