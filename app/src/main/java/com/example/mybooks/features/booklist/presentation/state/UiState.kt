package com.example.mybooks.features.booklist.presentation.state

import com.example.mybooks.features.booklist.domain.model.UserBooksModel

sealed class UiState {
    object Loading : UiState()
    data class Success(val userBooks: List<UserBooksModel>) : UiState()
    data class Error(val errorMessage: String) : UiState()
}