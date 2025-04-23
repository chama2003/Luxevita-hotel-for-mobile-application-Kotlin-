package com.example.luxe.roodb

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.RoomDatabase

class roomviewmodelfactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = roomdb.getInstance(application).room_dao()
        if (modelClass.isAssignableFrom(roomviewmodel::class.java)) {
            return roomviewmodel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
