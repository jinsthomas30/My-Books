package com.example.mybooks.features.booklist.presentation.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mybooks.R
import com.example.mybooks.features.booklist.domain.model.BookItem

@Composable
fun BookDetailsBottomSheetContent(book: BookItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Book cover image
        AsyncImage(
            model = book.coverId,
            contentDescription = "Cover for ${book.title}",
            modifier = Modifier
                .width(120.dp)
                .height(160.dp)
                .align(Alignment.CenterHorizontally),
            error = painterResource(R.drawable.image_error),
            placeholder = painterResource(R.drawable.placeholder),
        )

        Spacer(modifier = Modifier.height(16.dp))
        // Book title
        Text(
            text = book.title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Author
        Text(
            text = "By ${book.author}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))
        //Publish year
        Text(
            text = "First publish year ${book.firstPublish}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}