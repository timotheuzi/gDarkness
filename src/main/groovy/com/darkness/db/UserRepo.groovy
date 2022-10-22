package com.darkness.db

import org.springframework.data.repository.CrudRepository

interface UserRepo extends CrudRepository<UserDB, String> {
    UserDB findByNameIgnoreCase(String name)

    UserDB findById(Integer id)

    UserDB findByNameAndLocation(String name, Integer location)
}