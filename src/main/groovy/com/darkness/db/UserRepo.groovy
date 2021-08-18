package com.darkness.db

import org.springframework.data.repository.CrudRepository

interface UserRepo extends CrudRepository<UserDB, String> {
	UserDB findByUserName(String name)
	UserDB findById(Integer id)
	//UserDB findByLocation(Integer location)
}