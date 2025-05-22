package com.example.mybooks.features.booklist.domain.usecase

import com.example.mybooks.features.booklist.domain.model.UserBooksModel
import com.example.mybooks.features.booklist.domain.repository.GetUserBooksRepository
import com.example.mybooks.features.booklist.presentation.state.ResourceState
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetUserBooksUseCase @Inject constructor(val getUserBooksRepository: GetUserBooksRepository) {
    operator fun invoke(username: String): Observable<ResourceState<List<UserBooksModel>>> =
        getUserBooksRepository.getUserBooks(username)
}