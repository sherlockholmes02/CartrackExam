package com.davedecastro.cartrackexam.data.network

import com.davedecastro.cartrackexam.data.db.entities.User
import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

}