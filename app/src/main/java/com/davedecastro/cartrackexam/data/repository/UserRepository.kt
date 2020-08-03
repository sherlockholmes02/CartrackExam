package com.davedecastro.cartrackexam.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.davedecastro.cartrackexam.data.db.CartrackDatabase
import com.davedecastro.cartrackexam.data.db.entities.User
import com.davedecastro.cartrackexam.data.network.UserService
import com.davedecastro.cartrackexam.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class UserRepository(
    private val userService: UserService,
    private val cartrackDatabase: CartrackDatabase
) {

    private val users = MutableLiveData<List<User>>()

    init {
        users.observeForever{
            saveUsers(it)
        }
    }

    suspend fun getUsers(): LiveData<List<User>>{
        return withContext(Dispatchers.IO){
            fetchUsers()
            cartrackDatabase.userDao().getUsers()
        }
    }

    private suspend fun fetchUsers(){
        val response = userService.getUsers()
        users.postValue(response.body())
    }

    private fun saveUsers(users: List<User>) {
        Coroutines.inputOutput {
            cartrackDatabase.userDao().insertAllUsers(users)
        }
    }

}