package com.example.luxe.servicedb
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.luxe.roodb.room_dao
import com.example.luxe.roodb.room_info
import com.example.luxe.service
import kotlinx.coroutines.launch

class serviceviewmodel(private val dao: service_dao) : ViewModel() {
    val service: LiveData<List<service_info>> = dao.getAllData()

    fun insertservice(serviceInfo: service_info) = viewModelScope.launch {
        dao.insertservice(serviceInfo)
    }
    fun deleteservice (serviceInfo: service_info)=viewModelScope.launch {
        dao.deleteservice(serviceInfo)
    }

    suspend fun getBookedDatesForService(title: String): List<service_info> {
        return dao.getBookedDatesForService(title)
    }

}
