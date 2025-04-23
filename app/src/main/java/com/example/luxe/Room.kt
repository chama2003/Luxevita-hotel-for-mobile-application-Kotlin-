package com.example.luxe

data class Room(
    val title: String,
    val description: String,
    val price: Double, // This must be a Double
    val imageUrl: String,
    val roomType: String,
    val isAvailable: Boolean
)

