package com.darkness.db

import org.springframework.data.repository.CrudRepository

interface UserRepo extends CrudRepository<UserDB, Integer> {
	UserDB findByUserName(String name)
	//UserDB findByLocation(Integer location)
}