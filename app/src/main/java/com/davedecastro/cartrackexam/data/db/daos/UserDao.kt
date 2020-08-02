package com.davedecastro.cartrackexam.data.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.davedecastro.cartrackexam.data.db.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao : BaseDao<User> {

    @Query(
        """
            SELECT *
            FROM users
        """
    )
    override fun get(): List<User>

    @Query(
        """
            SELECT *
            FROM users
            WHERE id = :id
            LIMIT 1
        """
    )
    override fun get(id: Long): User

    @Query(
        """
            SELECT *
            FROM users
        """
    )
    override fun getFlow(): Flow<List<User>>

    @Query(
        """
            SELECT *
            FROM users
            WHERE id = :id
            LIMIT 1
        """
    )
    override fun getFlow(id: Long): Flow<User>

    @Query(
        """
            DELETE FROM users
        """
    )
    override fun clear()

    @Query(
        """
            SELECT *
            FROM users
            WHERE username = :username AND password = :password
            LIMIT 1
        """
    )
    fun loginUser(username: String, password: String): User
}