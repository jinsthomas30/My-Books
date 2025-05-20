package com.example.mybooks.features.booklist.domain.repository

import com.example.mybooks.features.booklist.domain.model.UserBooksModel
import com.example.mybooks.features.booklist.presentation.state.ResourceState
import kotlinx.coroutines.flow.Flow

interface GetUserBooksRepository {

    fun getUserBooks(username: String): Flow<ResourceState<List<UserBooksModel>>>
}