package com.example.luxe.signupdb
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "sign_information")

data class sign_info( @PrimaryKey(autoGenerate = true)
                      @ColumnInfo(name = "sign_id")
                      var id:Int,

                      @ColumnInfo(name = "sign_name")
                      var sname:String,
                      @ColumnInfo(name = "sign_address")
                      var saddress :String,
                      @ColumnInfo(name = "gender")
                      var sgender:String,
                      @ColumnInfo(name = "password")
                      var spass:String,


                      )
