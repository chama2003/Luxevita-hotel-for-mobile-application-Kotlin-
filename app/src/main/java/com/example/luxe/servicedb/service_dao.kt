package com.example.luxe.servicedb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.luxe.roodb.room_info

@Dao
interface service_dao {
    @Insert
    suspend fun insertservice(serviceInfo: service_info)

    @Delete
    suspend fun deleteservice(serviceInfo: service_info)
    @Query("SELECT * FROM bookingservice")
    fun getAllData(): LiveData<List<service_info>>
    @Query("SELECT * FROM bookingservice WHERE room_title = :title")
    suspend fun getBookedDatesForService(title: String): List<service_info>
}
