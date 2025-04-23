package com.example.luxe.roodb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.luxe.signupdb.sign_info

@Database(entities = [room_info::class], version = 1, exportSchema = false)

abstract class roomdb :RoomDatabase() {

    abstract fun room_dao(): room_dao

    companion object{
        @Volatile

        private var INTANCE: roomdb?=null

        fun getInstance(context: Context): roomdb {
            synchronized(this){
                var instance = INTANCE
                if(instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        roomdb::class.java,
                        "room add database").build()

                }
                return instance
            }

        }
    }
}