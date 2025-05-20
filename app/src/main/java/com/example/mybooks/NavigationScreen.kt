package com.example.mybooks

enum class NavigationScreen{
    USER_BOOKS_SCREEN
}
sealed class Screens(val route : String) {
    data object USER_BOOKS_SCREEN: Screens(NavigationScreen.USER_BOOKS_SCREEN.name)
}