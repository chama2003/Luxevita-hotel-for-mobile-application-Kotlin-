package com.example.luxe.signupdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [sign_info::class], version = 1, exportSchema = false)
abstract  class signdb :RoomDatabase() {

    abstract fun sign_dao(): sign_dao

    companion object{
        @Volatile

        private var INTANCE: signdb?=null

        fun getInstance(context: Context): signdb {
            synchronized(this){
                var instance = INTANCE
                if(instance==null){
                    instance=Room.databaseBuilder(
                        context.applicationContext,
                        signdb::class.java,
                        "food add database").build()

                }
                return instance
            }

        }
    }
}