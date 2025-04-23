package com.example.luxe.servicedb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar
import java.util.Date

@Entity(tableName = "bookingservice")


data class service_info(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Booking_id")
    var id: Int = 0, // No need to provide an id when inserting, it will be auto-generated

    @ColumnInfo(name = "user_name")
    var username: String,

    @ColumnInfo(name = "room_title")
    var title: String,

    @ColumnInfo(name = "price")
    var price: Double,
    @ColumnInfo(name = "date")
    var date: Long
)
