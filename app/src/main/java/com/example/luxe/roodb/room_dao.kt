package com.example.luxe.roodb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface room_dao {
    @Insert
    suspend fun insertroom(roomInfo: room_info)
    @Delete
    suspend fun deleteroom(roomInfo: room_info)

    @Query("SELECT * FROM bookings")
    fun getAllData(): LiveData<List<room_info>>
}
