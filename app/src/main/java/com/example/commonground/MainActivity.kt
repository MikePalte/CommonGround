package com.example.commonground

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.lifecycle.ViewModelProviders
import com.example.commonground.DTO.CalendarEvent
import com.example.commonground.DTO.MyAdapter
import com.example.commonground.ui.main.MainFragment
import com.example.commonground.ui.main.MainViewModel
import com.google.firebase.Timestamp
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       /* if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        */

        supportActionBar?.title = "Title"


        var listview = findViewById<ListView>(R.id.main_listview)

        var list = mutableListOf<CalendarEvent>()

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.events.observe(this, androidx.lifecycle.Observer {
            events -> events.forEach {
            list.add(it)
            listview.adapter = MyAdapter(this, R.layout.row, list)

        }
        })

    }

    fun addNewEvent(view: View) {
        val intent = Intent(applicationContext, AddEventActivity::class.java)

     //   intent.putExtra("input", editEventName.text)
        startActivity(intent)

    }
}
