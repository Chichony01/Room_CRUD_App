package com.example.roomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.db.Person
import com.example.roomapp.db.PersonDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var clearButton: Button
    private lateinit var personRecyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var selectedPerson: Person
    private var isListItemClicked = false

    private lateinit var viewModel: PersonViewModel         // instance of view model
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.etName)
        emailEditText = findViewById(R.id.etEmail)
        saveButton = findViewById(R.id.btnSave)
        clearButton = findViewById(R.id.btnClear)
        personRecyclerView = findViewById(R.id.rvPerson)

        /* now we are gonna get instance of view model using view model provider and to do that firstly we need to pass an instance of view model factory to the viewModel provider
        but to construct a view model factory we need a dao instance  */

        val dao = PersonDatabase.getInstance(application).personDao()
        val factory = PersonViewModelFactory(dao)
        viewModel = ViewModelProvider(this , factory).get(PersonViewModel::class.java)

        saveButton.setOnClickListener {
            if(isListItemClicked){
                updatePerson()
                clearInput()
            }
            else {
                savePersonData()
                clearInput()
            }
        }

        clearButton.setOnClickListener {
            if(isListItemClicked){
                deletePerson()
                clearInput()
            }
            else {
                clearInput()
            }
        }

        initRecyclerView()
    }

    //  Setting function of two buttons
    private fun savePersonData(){
/*      val name = nameEditText.text.toString()
        val email = emailEditText.text.toString()
        val person = Person(0,name, email)
        viewModel.insertPerson(person)                      */
        // instead of above long code we can just write
        viewModel.insertPerson(
            Person(
                0,
                nameEditText.text.toString(),
                emailEditText.text.toString()
            )
        )
    }

    private fun updatePerson(){
        viewModel.updatePerson(
            Person(
                selectedPerson.id ,
                nameEditText.text.toString(),
                emailEditText.text.toString()
            )
        )
        //  after updating part is done we need to take back the screen to default
        saveButton.text = "Save"      // setting the text of button after selecting
        clearButton.text = "Clear"
        isListItemClicked = false
    }

    private fun deletePerson(){
        viewModel.deletePerson(
            Person(
                selectedPerson.id ,
                nameEditText.text.toString(),
                emailEditText.text.toString()
            )
        )
        //  after updating part is done we need to take back the screen to default
        saveButton.text = "Save"      // setting the text of button after selecting
        clearButton.text = "Clear"
        isListItemClicked = false
    }

    private fun clearInput(){
        nameEditText.setText("")
        emailEditText.setText("")
    }

    private fun initRecyclerView(){
        personRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerViewAdapter{
            selectedItem: Person -> listItemClicked(selectedItem)
        }
        personRecyclerView.adapter = adapter
        displayPersonList()
    }

    private fun displayPersonList(){
        // inside this function we will write code to observe the list from person viewModel
        viewModel.person.observe(this,{
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(person:Person){
        //Toast.makeText(this ,"Student name is ${person.name}" , Toast.LENGTH_SHORT).show()
        selectedPerson = person
        saveButton.text = "Update"      // setting the text of button after selecting
        clearButton.text = "Delete"
        isListItemClicked = true
        nameEditText.setText(selectedPerson.name)
        emailEditText.setText(selectedPerson.email)
    }
}