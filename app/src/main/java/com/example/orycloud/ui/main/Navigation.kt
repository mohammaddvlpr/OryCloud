package com.example.orycloud.ui.main


enum class Screen {
    MAIN,
    LOGIN,
    REGISTER
}
sealed class NavigationItem(val route: String) {
    data object Main : NavigationItem(Screen.MAIN.name)
    data object Login : NavigationItem(Screen.LOGIN.name)
    data object Register : NavigationItem(Screen.REGISTER.name)
}