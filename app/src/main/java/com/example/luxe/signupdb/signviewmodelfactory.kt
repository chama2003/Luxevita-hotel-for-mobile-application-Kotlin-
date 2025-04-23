package com.example.luxe.signupdb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
class signviewmodelfactory  (
    private val dao: sign_dao
)  : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(signviewmodel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return signviewmodel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}