package com.example.mybooks.features.booklist.data.remote

import com.example.mybooks.features.booklist.data.model.MyBookResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("people/{username}/books/want-to-read.json")
    suspend fun getUserBooks(@Path("username") username: String): MyBookResponse
}