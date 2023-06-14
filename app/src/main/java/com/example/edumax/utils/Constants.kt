package com.example.edumax.utils

object Constants {
    private const val PORT = "5001"

    /// ----- Emulator ----- ///
    const val BASE_URL = "http://10.0.2.2:$PORT"

    /// ------ Device ------ ///
    //const val BASE_URL = "http://192.168.1.233:$PORT/"

    /// ------ Heroku ------ ///
    //const val BASE_URL = "https://chicky-app.herokuapp.com/"

    const val BASE_URL_IMAGES = BASE_URL + "/uploads/"


    const val SHARED_PREF_SESSION = "SESSION"
}