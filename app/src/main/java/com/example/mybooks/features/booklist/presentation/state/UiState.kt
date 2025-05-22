package com.example.mybooks.features.booklist.presentation.state

import com.example.mybooks.features.booklist.domain.model.BookItem

sealed class UiState {
    object Loading : UiState()
    data class Success(val userBooks: List<BookItem>) : UiState()
    data class Error(val errorMessage: String,val errorType: ErrorType) : UiState()
}