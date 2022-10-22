package com.darkness.db

import org.springframework.data.repository.CrudRepository

interface CacheRepo extends CrudRepository<CacheDB, Integer> {
    //CacheDB findById(Integer id)
}