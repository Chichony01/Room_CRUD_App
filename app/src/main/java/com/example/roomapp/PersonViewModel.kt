// To provide data and to recieve data we need to create a view model

package com.example.roomapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomapp.db.Person
import com.example.roomapp.db.PersonDao
import kotlinx.coroutines.launch

class PersonViewModel(private val dao: PersonDao) : ViewModel() {
    val person = dao.getAllPerson()

    fun insertPerson(person: Person) = viewModelScope.launch {      // suspend function calling in coroutines
        dao.insertPerson(person)
    }

    fun updatePerson(person: Person) = viewModelScope.launch {      // suspend function calling in coroutines
        dao.updatePerson(person)
    }
    fun deletePerson(person: Person) = viewModelScope.launch {      // suspend function calling in coroutines
        dao.deletePerson(person)
    }
}