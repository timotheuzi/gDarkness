package com.darkness.db

import org.springframework.data.repository.CrudRepository

interface UserRepo extends CrudRepository<UserDB, String> {
	Optional<UserDB> findByName(String name)
	Optional<UserDB> findById(Integer id)
	Optional<UserDB> findByNameAndLocation(String name, Integer location)
}