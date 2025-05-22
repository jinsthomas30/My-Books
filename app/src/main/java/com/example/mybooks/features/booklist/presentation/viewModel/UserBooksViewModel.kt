package com.example.mybooks.features.booklist.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.mybooks.features.booklist.domain.usecase.BooksUseCase
import com.example.mybooks.features.booklist.presentation.state.ResultState
import com.example.mybooks.features.booklist.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class UserBooksViewModel @Inject constructor(
    private val booksUseCase: BooksUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        getUserBooks("mekBot")
    }

    fun getUserBooks(username: String) {
        val disposable = booksUseCase(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                val state = when (result) {
                    is ResultState.Success -> UiState.Success(result.data)
                    is ResultState.Error -> UiState.Error(result.message ?: "Unknown error")
                    is ResultState.Loading -> UiState.Loading
                }
                _uiState.value = state
            }

        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}