package com.example.luxe.roodb

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class roomviewmodel(private val dao: room_dao) : ViewModel() {
    val room: LiveData<List<room_info>> = dao.getAllData()

    fun insertroom(roomInfo: room_info) = viewModelScope.launch {
        dao.insertroom(roomInfo)
    }
    fun deleteroom (roomInfo: room_info)=viewModelScope.launch {
        dao.deleteroom(roomInfo)
    }
}
