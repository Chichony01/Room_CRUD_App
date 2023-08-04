package com.example.roomapp

import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.db.PersonDao
import java.lang.IllegalArgumentException

class PersonViewModelFactory(private val dao: PersonDao): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PersonViewModel::class.java)){
            return PersonViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}