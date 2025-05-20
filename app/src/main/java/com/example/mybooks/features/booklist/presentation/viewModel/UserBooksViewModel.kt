package com.example.mybooks.features.booklist.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybooks.features.booklist.domain.usecase.GetUserBooksUseCase
import com.example.mybooks.features.booklist.presentation.state.ResourceState
import com.example.mybooks.features.booklist.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserBooksViewModel @Inject constructor(val getUserBooksUseCase: GetUserBooksUseCase) :
    ViewModel() {
    val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        getUserBooks()
    }

    private fun getUserBooks() {
        try {
            viewModelScope.launch {
                getUserBooksUseCase("jins719").collect { resource ->
                    _uiState.value =
                        when (resource) {
                            is ResourceState.Loading -> UiState.Loading
                            is ResourceState.Success -> UiState.Success(
                                resource.data ?: emptyList()
                            )

                            is ResourceState.Error -> UiState.Error(
                                resource.message ?: "Unknown error"
                            )
                        }
                }
            }

        } catch (e: Exception) {
            _uiState.value = UiState.Error(e.message ?: "Unknown error")
        }

    }
}