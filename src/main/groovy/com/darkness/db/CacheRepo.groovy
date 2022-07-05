package com.darkness.db

import org.springframework.data.repository.CrudRepository

 interface CacheRepo extends CrudRepository<CacheDB, Integer> {
	Optional<CacheDB> findById(Integer id)
}