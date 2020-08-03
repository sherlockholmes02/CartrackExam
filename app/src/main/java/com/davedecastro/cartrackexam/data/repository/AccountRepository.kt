package com.davedecastro.cartrackexam.data.repository

import com.davedecastro.cartrackexam.data.db.CartrackDatabase
import com.davedecastro.cartrackexam.data.db.entities.Account

class AccountRepository(private val cartrackDatabase: CartrackDatabase) {

    suspend fun accountLogin(username: String, password: String): Account? {
        return cartrackDatabase.accountDao().loginAccount(username, password)
    }

    suspend fun checkIfUserIsPopulated(): Int {
        return cartrackDatabase.accountDao().get().size
    }

    suspend fun insertAccount(account: Account) {
        return cartrackDatabase.accountDao().insert(account)
    }
}