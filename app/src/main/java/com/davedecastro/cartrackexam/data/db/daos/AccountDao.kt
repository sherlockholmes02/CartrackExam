package com.davedecastro.cartrackexam.data.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.davedecastro.cartrackexam.data.db.entities.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao : BaseDao<Account> {

    @Query(
        """
            SELECT *
            FROM accounts
        """
    )
    override fun get(): List<Account>

    @Query(
        """
            SELECT *
            FROM accounts
            WHERE id = :id
            LIMIT 1
        """
    )
    override fun get(id: Long): Account

    @Query(
        """
            SELECT *
            FROM accounts
        """
    )
    override fun getFlow(): Flow<List<Account>>

    @Query(
        """
            SELECT *
            FROM accounts
            WHERE id = :id
            LIMIT 1
        """
    )
    override fun getFlow(id: Long): Flow<Account>

    @Query(
        """
            DELETE FROM accounts
        """
    )
    override fun clear()

    @Query(
        """
            SELECT *
            FROM accounts
            WHERE username = :username AND password = :password
            LIMIT 1
        """
    )
    fun loginAccount(username: String, password: String): Account
}