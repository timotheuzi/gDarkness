package com.darkness.db

import org.springframework.data.repository.CrudRepository

 interface ItemsRepo extends CrudRepository<ItemsDB, Integer> {
	ItemsDB findByItemName(String name)
}