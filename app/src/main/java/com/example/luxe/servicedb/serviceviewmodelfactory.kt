package com.example.luxe.servicedb


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.RoomDatabase
import com.example.luxe.roodb.roomdb
import com.example.luxe.roodb.roomviewmodel

class serviceviewmodelfactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = servicedb.getInstance(application).service_dao()
        if (modelClass.isAssignableFrom(serviceviewmodel::class.java)) {
            return serviceviewmodel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
