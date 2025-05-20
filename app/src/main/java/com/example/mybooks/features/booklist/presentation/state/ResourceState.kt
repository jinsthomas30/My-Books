package com.example.mybooks.features.booklist.presentation.state

sealed class ResourceState<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : ResourceState<T>(data)
    class Success<T>(data: T?) : ResourceState<T>(data)
    class Error<T>(data: T? = null, message: String) : ResourceState<T>(data, message)
}