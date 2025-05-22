package com.example.mybooks.features.booklist.domain.repository

import com.example.mybooks.features.booklist.domain.model.UserBooksModel
import com.example.mybooks.features.booklist.presentation.state.ResourceState
import io.reactivex.rxjava3.core.Observable

interface GetUserBooksRepository {
    fun getUserBooks(username: String): Observable<ResourceState<List<UserBooksModel>>>
}