package com.example.luxe.roodb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "bookings")


data class room_info(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Booking_id")
    var id: Int = 0, // No need to provide an id when inserting, it will be auto-generated

    @ColumnInfo(name = "user_name")
    var username: String,

    @ColumnInfo(name = "room_title")
    var title: String,

    @ColumnInfo(name = "price")
    var price: Double
)
