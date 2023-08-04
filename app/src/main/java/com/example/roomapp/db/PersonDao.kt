// DAO --> Data Access Object. This is where we define functions like to add ,delete,update,read
// since we want updatation,deletetion etc to work in the background we need coroutines. Coroutines helps to work these function in different thread not on main thread
// and for coroutines we have to make functions suspend

package com.example.roomapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonDao {
    @Insert
    suspend fun insertPerson(person: Person)

    @Update
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

    @Query("SELECT * FROM person_data_table")   // unlike other 3 fucntions we need to write sql query for this function
    fun getAllPerson(): LiveData<List<Person>>

}