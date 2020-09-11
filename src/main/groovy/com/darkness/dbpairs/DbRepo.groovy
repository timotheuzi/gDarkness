package com.darkness.db

import com.darkness.dbpairs.DbPogo;
import org.springframework.data.repository.CrudRepository;

public interface DbRepo extends CrudRepository<DbPogo, Integer> {
    DbPogo findByName(String name);

    DbPogo findByLocation(Integer location);
}