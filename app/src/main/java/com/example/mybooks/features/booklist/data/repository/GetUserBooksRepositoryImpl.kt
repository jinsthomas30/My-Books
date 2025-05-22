package com.example.mybooks.features.booklist.data.repository

import com.example.mybooks.features.booklist.data.mapper.BooksResponseMapper
import com.example.mybooks.features.booklist.data.remote.ApiService
import com.example.mybooks.features.booklist.domain.model.UserBooksModel
import com.example.mybooks.features.booklist.domain.repository.GetUserBooksRepository
import com.example.mybooks.features.booklist.presentation.state.ResourceState
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetUserBooksRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : GetUserBooksRepository {

    override fun getUserBooks(username: String): Observable<ResourceState<List<UserBooksModel>>> {
        return apiService.getUserBooks(username)
            .map<ResourceState<List<UserBooksModel>>> { response ->
                val mapper = BooksResponseMapper()
                val userBooks = mapper.mapToDomain(response)
                ResourceState.Success(userBooks)
            }
            .onErrorReturn { throwable ->
                ResourceState.Error(throwable.message ?: "Unknown Error")
            }
            .startWithItem(ResourceState.Loading)
    }
}