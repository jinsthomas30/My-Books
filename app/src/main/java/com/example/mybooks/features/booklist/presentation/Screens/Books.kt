package com.example.mybooks.features.booklist.presentation.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mybooks.R
import com.example.mybooks.features.booklist.domain.model.BookItem
import com.example.mybooks.features.booklist.presentation.state.UiState
import com.example.mybooks.features.booklist.presentation.viewModel.BooksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Books(viewModel: BooksViewModel = hiltViewModel()) {
    val bottomSheetState = rememberModalBottomSheetState()
    var selectedBook by remember { mutableStateOf<BookItem?>(null) }
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(stringResource(R.string.app_name))
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
        )
    }) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            when (val state = uiState) {
                is UiState.Loading -> Loading()
                is UiState.Success -> {
                    // Show BottomSheet only when selectedBook is not null
                    selectedBook?.let { book ->
                        ModalBottomSheet(
                            onDismissRequest = {
                                selectedBook = null
                            },
                            sheetState = bottomSheetState
                        ) {
                            BookDetailsBottomSheetContent(book)
                        }
                    }
                    BooksList(state.Books, onItemClick = { selectedBook = it })
                }

                is UiState.Error -> ErrorMessage(state.errorMessage)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksList(Books: List<BookItem>, onItemClick: (BookItem) -> Unit) {
    Column {
        if (Books.isEmpty()) {
            Box(Modifier.fillMaxSize(), Alignment.Center) {
                Text(
                    stringResource(R.string.no_books_available),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        } else {
            // List
            LazyColumn(contentPadding = PaddingValues(bottom = 16.dp)) {
                items(Books) { books ->
                    ListItem(books, onItemClick)
                }
            }
        }
    }
}

@Composable
fun ListItem(books: BookItem, onItemClick: (BookItem) -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .fillMaxWidth()
            .clickable { onItemClick(books) }
    ) {
        Column {
            Row {
                // Book cover image
                AsyncImage(
                    model = books.coverId,
                    contentDescription = "Cover for ${books.title}",
                    modifier = Modifier
                        .height(80.dp)
                        .width(60.dp),
                    contentScale = ContentScale.Crop,
                    error = painterResource(R.drawable.image_error),
                    placeholder = painterResource(R.drawable.placeholder),
                )
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    // Book title
                    Text(
                        text = books.title,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(Modifier.height(8.dp))
                    // Book Author
                    Text(
                        text = "By ${books.author}",
                        color = Color.Black
                    )
                }
            }
        }

    }
}

@Composable
fun ErrorMessage(message: String) {
    Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {
        Text(message, color = Color.Red, textAlign = TextAlign.Center)
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag("loading_indicator"), Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}