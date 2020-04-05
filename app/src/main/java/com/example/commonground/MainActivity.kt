package com.example.commonground

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.lifecycle.ViewModelProviders
import com.example.commonground.DTO.CalendarEvent
import com.example.commonground.DTO.MyAdapter
import com.example.commonground.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.supportActionBar?.title = getString(R.string.title)


        val listView = findViewById<ListView>(R.id.main_listview)

        val list = mutableListOf<CalendarEvent>()


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.events.observe(this, androidx.lifecycle.Observer {
            events -> events.forEach {
            list.add(it)
            listView.adapter = MyAdapter(this, R.layout.row, list)

        }
        })

    }


    fun addNewEvent() {
        val intent = Intent(applicationContext, AddEventActivity::class.java)

        startActivity(intent)

    }
}
