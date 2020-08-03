package com.davedecastro.cartrackexam.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUsers(users: List<User>)

    @Query("SELECT * FROM users")
    fun getUsers(): LiveData<List<User>>
}