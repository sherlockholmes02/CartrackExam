package com.davedecastro.cartrackexam.data.repository

import com.davedecastro.cartrackexam.data.db.CartrackDatabase
import com.davedecastro.cartrackexam.data.db.entities.User

class UserRepository(private val cartrackDatabase: CartrackDatabase) {

    suspend fun userLogin(username: String, password: String): User? {
        return cartrackDatabase.userDao().loginUser(username, password)
    }

    suspend fun checkIfUserIsPopulated(): Int {
        return cartrackDatabase.userDao().get().size
    }

    suspend fun inserUser(user: User) {
        return cartrackDatabase.userDao().insert(user)
    }

}