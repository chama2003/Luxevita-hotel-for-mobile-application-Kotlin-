package com.example.luxe.signupdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
class signviewmodel (private val dao: sign_dao):ViewModel() {

    var sign= dao.getAllData()
    fun insertsign(foodInfo: sign_info)=viewModelScope.launch {
        dao.insertsign(foodInfo)
    }
    fun updatesign(signInfo: sign_info) {
        viewModelScope.launch {
            dao.update(signInfo)
        }
    }

    fun deletesign (foodInfo: sign_info)=viewModelScope.launch {
        dao.deletesign(foodInfo)
    }
    fun getUser(sname: String, password: String): sign_info? {
        return runBlocking {
            withContext(Dispatchers.IO) {
                dao.getUser(sname, password)
            }
        }
    }
    fun getUsername(sname: String): sign_info? {
        return runBlocking {
            withContext(Dispatchers.IO) {
                dao.getUsername(sname)
            }
        }
    }
    fun getaddress(sname: String, password: String): String? {
        return runBlocking {
            withContext(Dispatchers.IO) {
                dao.getaddress(sname, password)
            }
        }
    }


}