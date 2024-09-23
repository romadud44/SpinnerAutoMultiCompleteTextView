package com.example.spinnerautomulticompletetextview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spinnerautomulticompletetextview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var listAdapter: ListAdapter? = null
    private var personsList = mutableListOf<Person>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.toolbarMain.title = "Подбор персонала"
        binding.toolbarMain.subtitle = "версия 1.0"
        setSupportActionBar(binding.toolbarMain)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, Person.positionsArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.positionSP.adapter = adapter


        binding.saveBTN.setOnClickListener {
            addPerson()
            listAdapter = ListAdapter(this, personsList)
            binding.listViewLV.adapter = listAdapter
            listAdapter?.notifyDataSetChanged()
            clearEditFields()
        }
        binding.listViewLV.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, pos, _ ->
                val removePerson = listAdapter?.getItem(pos)
                listAdapter?.remove(removePerson)
                Toast.makeText(this, "Запись удалена $removePerson", Toast.LENGTH_LONG).show()

            }
    }

    private fun clearEditFields() {
        binding.nameET.text.clear()
        binding.lastNameET.text.clear()
        binding.ageET.text.clear()
    }

    private fun addPerson() {
        val name = binding.nameET.text.toString()
        val lastName = binding.lastNameET.text.toString()
        val age = binding.ageET.text.toString()
        val position = binding.positionSP.selectedItem.toString()
        val person = Person(name, lastName, age, position)
        personsList.add(person)
        Toast.makeText(this, "Сотрудник добавлен: $person", Toast.LENGTH_LONG).show()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuMain -> {
                finishAndRemoveTask()
                finishAffinity()
                finish()
                Toast.makeText(this, "Программа завершена", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}