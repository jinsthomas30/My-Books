package com.example.mybooks.features.booklist.data.mapper

import com.example.mybooks.features.booklist.data.model.BookResponse
import com.example.mybooks.features.booklist.domain.model.BookItem

class BooksResponseMapper {
    fun mapToDomain(response: BookResponse): List<BookItem> {
        return response.reading_log_entries?.mapNotNull { entry ->
            entry?.work?.let {
                BookItem(
                    title = it.title ?: "Unknown Title",
                    author = it.author_names?.firstOrNull() ?: "Unknown Author",
                    coverId = it.cover_id.toString(),
                    firstPublish = it.first_publish_year.toString()
                )
            }

        } ?: emptyList()
    }
}