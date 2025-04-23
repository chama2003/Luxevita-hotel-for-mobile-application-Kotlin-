package com.example.luxe.signupdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface sign_dao {
    @Insert
    suspend fun insertsign(signInfo: sign_info)

    @Update
    suspend fun update(signInfo: sign_info)

    @Delete
    suspend fun deletesign(signInfo: sign_info)

    @Query("SELECT * FROM sign_information")
    fun getAllData(): LiveData<List<sign_info>>

    @Query("SELECT * FROM sign_information WHERE sign_name = :sname AND password = :password LIMIT 1")
    fun getUser(sname: String, password: String): sign_info?

    // Updated to return a String for `sign_address`
    @Query("SELECT sign_address FROM sign_information WHERE sign_name = :sname AND password = :password LIMIT 1")
    fun getaddress(sname: String, password: String): String?

    @Query("SELECT * FROM sign_information WHERE sign_name = :sname LIMIT 1")
    fun getUsername(sname: String): sign_info?
}
