package com.example.mybooks.features.booklist.data.repository

import com.example.mybooks.features.booklist.data.mapper.BooksResponseMapper
import com.example.mybooks.features.booklist.data.remote.ApiService
import com.example.mybooks.features.booklist.domain.model.BookItem
import com.example.mybooks.features.booklist.domain.repository.BookRepository
import com.example.mybooks.features.booklist.presentation.state.ErrorType
import com.example.mybooks.features.booklist.presentation.state.ResultState
import io.reactivex.rxjava3.core.Observable
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : BookRepository {

    override fun getBooks(username: String): Observable<ResultState<List<BookItem>>> {
        return apiService.getBooks(username)
            .map<ResultState<List<BookItem>>> { response ->
                val mapper = BooksResponseMapper()
                val Books = mapper.mapToDomain(response)
                ResultState.Success(Books)
            }
            .onErrorReturn { throwable ->
                val (message, errorType) = when (throwable) {
                    is UnknownHostException -> "No internet connection" to ErrorType.NETWORK
                    is SocketTimeoutException  -> "Request timed out" to ErrorType.TIMEOUT
                    else -> throwable.localizedMessage to ErrorType.UNKNOWN
                }
                ResultState.Error(message,errorType)
            }
            .startWithItem(ResultState.Loading)
    }
}