package com.example.mybooks

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mybooks.features.booklist.presentation.Screens.Books

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.USER_BOOKS_SCREEN.route) {
        composable(route = Screens.USER_BOOKS_SCREEN.route) {
            Books()
        }
    }
}