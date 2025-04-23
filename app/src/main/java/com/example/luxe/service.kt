package com.example.luxe

import java.util.Calendar

data class service(
    val title: String,
    val description: String,
    val price: Double, // This must be a Double
    val imageUrl: String,
    val serviceType: String,
    val isAvailable: Boolean,
    val availableDates: List<Calendar> = emptyList()
)
