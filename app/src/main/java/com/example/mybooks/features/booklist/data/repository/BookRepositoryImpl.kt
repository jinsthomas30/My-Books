package com.example.mybooks.features.booklist.data.repository

import com.example.mybooks.features.booklist.data.mapper.BooksResponseMapper
import com.example.mybooks.features.booklist.data.remote.ApiService
import com.example.mybooks.features.booklist.domain.model.BookItem
import com.example.mybooks.features.booklist.domain.repository.BookRepository
import com.example.mybooks.features.booklist.presentation.state.ErrorType
import com.example.mybooks.features.booklist.presentation.state.ResultState
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : BookRepository {

    override fun getUserBooks(username: String): Observable<ResultState<List<BookItem>>> {
        return apiService.getUserBooks(username)
            .map<ResultState<List<BookItem>>> { response ->
                val mapper = BooksResponseMapper()
                val userBooks = mapper.mapToDomain(response)
                ResultState.Success(userBooks)
            }
            .onErrorReturn { throwable ->
                val (message, errorType) = when (throwable) {
                    is java.net.UnknownHostException -> "No internet connection" to ErrorType.NETWORK
                    is java.net.SocketTimeoutException -> "Request timed out" to ErrorType.TIMEOUT
                    else -> throwable.localizedMessage to ErrorType.UNKNOWN
                }
                ResultState.Error(message,errorType)
            }
            .startWithItem(ResultState.Loading)
    }
}