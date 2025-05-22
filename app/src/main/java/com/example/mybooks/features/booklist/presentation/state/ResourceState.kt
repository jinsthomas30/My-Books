package com.example.mybooks.features.booklist.presentation.state

sealed class ResourceState<out T> {
    object Loading : ResourceState<Nothing>()
    data class Success<T>(val data: T) : ResourceState<T>()
    data class Error(val message: String?) : ResourceState<Nothing>()
}