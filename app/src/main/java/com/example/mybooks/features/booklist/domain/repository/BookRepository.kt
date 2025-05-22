package com.example.mybooks.features.booklist.domain.repository

import com.example.mybooks.features.booklist.domain.model.BookItem
import com.example.mybooks.features.booklist.presentation.state.ResultState
import io.reactivex.rxjava3.core.Observable

interface BookRepository {
    fun getUserBooks(username: String): Observable<ResultState<List<BookItem>>>
}