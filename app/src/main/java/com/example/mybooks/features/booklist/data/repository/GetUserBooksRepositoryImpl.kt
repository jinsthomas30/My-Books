package com.example.mybooks.features.booklist.data.repository

import com.example.mybooks.features.booklist.data.mapper.BooksResponseMapper
import com.example.mybooks.features.booklist.data.remote.ApiService
import com.example.mybooks.features.booklist.domain.model.UserBooksModel
import com.example.mybooks.features.booklist.domain.repository.GetUserBooksRepository
import com.example.mybooks.features.booklist.presentation.state.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserBooksRepositoryImpl @Inject constructor(val apiService: ApiService) :
    GetUserBooksRepository {
    override fun getUserBooks(username: String): Flow<ResourceState<List<UserBooksModel>>> = flow {
        try {
            emit(ResourceState.Loading())
            val mapper = BooksResponseMapper()
            val response = apiService.getUserBooks(username)
            val userBooks = mapper.mapToDomain(response)
            emit(ResourceState.Success(userBooks))

        } catch (e: Exception) {
            emit(ResourceState.Error(message = e.message ?: "UnKnown Error"))
        }
    }
}