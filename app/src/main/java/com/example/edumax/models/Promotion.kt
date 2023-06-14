package com.example.edumax.models

import java.io.Serializable
import java.util.*

data class Promotion(
    var date: Date,
    var title: String,
    var subtitle: String,
    var description: String,
    var percentage: String,
    var image: String,
) : Serializable