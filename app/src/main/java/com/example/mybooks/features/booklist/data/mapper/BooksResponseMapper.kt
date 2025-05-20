package com.example.mybooks.features.booklist.data.mapper

import com.example.mybooks.features.booklist.data.model.MyBookResponse
import com.example.mybooks.features.booklist.domain.model.UserBooksModel

class BooksResponseMapper {
    fun mapToDomain(response: MyBookResponse): List<UserBooksModel> {
        return response.reading_log_entries?.mapNotNull { entry ->
            entry?.work?.let {
                UserBooksModel(
                    title = it.title ?: "Unknown Title",
                    author = it.author_names?.firstOrNull() ?: "Unknown Author",
                    coverId = it.cover_id.toString(),
                    firstPublish = it.first_publish_year.toString()
                )
            }

        } ?: emptyList()
    }
}