package com.example.luxe.servicedb
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.luxe.roodb.room_dao
import com.example.luxe.roodb.room_info
import com.example.luxe.signupdb.sign_info

@Database(entities = [service_info::class], version = 1, exportSchema = false)

abstract class servicedb :RoomDatabase() {

    abstract fun service_dao(): service_dao

    companion object{
        @Volatile

        private var INTANCE: servicedb?=null

        fun getInstance(context: Context): servicedb {
            synchronized(this){
                var instance = INTANCE
                if(instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        servicedb::class.java,
                        "service add database").build()

                }
                return instance
            }

        }
    }
}