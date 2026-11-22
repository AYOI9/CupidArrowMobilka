package com.example.cupidarrow.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object GetStarted : Screen("get_started")
    object Login : Screen("login")
    object PhoneInput : Screen("phone_input")
    object Otp : Screen("otp")
    object Name : Screen("name")
    object Email : Screen("email")
    object Age : Screen("age")
    object Gender : Screen("gender")
    object LookingFor : Screen("looking_for")
    object Interests : Screen("interests")
    object PhotoUpload : Screen("photo_upload")
    object Location : Screen("location")
    object MainFeed : Screen("main_feed")
}
