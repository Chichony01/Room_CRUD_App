// creating entity(i.e data table)

package com.example.roomapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_data_table")
data class Person(              // In data class we declare variable as constructor parameters
    @ColumnInfo(name = "Person_id")
    @PrimaryKey(autoGenerate = true)        // this will auto generate id(i.e numbers)
    var id : Int ,

    @ColumnInfo(name = "Person_name")
    var name: String ,

    @ColumnInfo(name = "Person_email")
    var email : String
)
