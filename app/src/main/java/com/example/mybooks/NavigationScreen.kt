package com.example.mybooks

enum class NavigationScreen{
    USER_BOOKS_SCREEN,USER_BOOKS_DETAILS_SCREEN
}
sealed class Screens(val route : String) {
    data object USER_BOOKS_SCREEN: Screens(NavigationScreen.USER_BOOKS_SCREEN.name)
    data object USER_BOOKS_DETAILS_SCREEN: Screens(NavigationScreen.USER_BOOKS_DETAILS_SCREEN.name)
}