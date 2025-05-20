package com.example.mybooks.features.booklist.data.model

import com.example.mybooks.features.booklist.domain.model.UserBooksModel

data class MyBookResponse(
    val numFound: Int?,
    val page: Int?,
    val reading_log_entries: List<ReadingLogEntry?>?
)

data class ReadingLogEntry(
    val logged_date: String?,
    val logged_edition: String?,
    val work: Work?
)

data class Work(
    val author_keys: List<String?>?,
    val author_names: List<String?>?,
    val cover_edition_key: String?,
    val cover_id: Int?,
    val edition_key: List<String?>?,
    val first_publish_year: Int?,
    val key: String?,
    val lending_edition_s: String?,
    val title: String?
)
