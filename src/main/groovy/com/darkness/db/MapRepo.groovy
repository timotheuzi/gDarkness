package com.darkness.db;

import org.springframework.data.repository.CrudRepository;

//import com.darkness.db.mapDB;

interface MapRepo extends CrudRepository<MapDB, Integer> {
	// mapDB findByName(String name);

	// Optional<mapDB> findById(Integer intValue);
}