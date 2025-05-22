package com.example.mybooks.features.booklist.domain.usecase

import com.example.mybooks.features.booklist.domain.model.BookItem
import com.example.mybooks.features.booklist.domain.repository.BookRepository
import com.example.mybooks.features.booklist.presentation.state.ResultState
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class BooksUseCase @Inject constructor(val bookRepository: BookRepository) {
    operator fun invoke(username: String): Observable<ResultState<List<BookItem>>> =
        bookRepository.getBooks(username)
}