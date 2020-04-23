package com.example.commonground

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.commonground.DTO.CalendarEvent
import com.example.commonground.DTO.MyAdapter
import com.example.commonground.ui.main.MainViewModel
import com.google.firebase.Timestamp
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    lateinit var calendar: Calendar
    lateinit var calendarView: CalendarView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment.newInstance())
//                .commitNow()
//        }

        supportActionBar?.title = getString(R.string.title)


        var listView = findViewById<ListView>(R.id.main_listview)

        var list = mutableListOf<CalendarEvent>()


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.events.observe(this, androidx.lifecycle.Observer {
            list = mutableListOf<CalendarEvent>();
            if (it.size == 0){
                list.add(CalendarEvent("No events today.", Timestamp(calendar.time)))
                listView.adapter = MyAdapter(this, R.layout.row, list)
            } else {
                it.forEach {
                    list.add(it)
                    listView.adapter = MyAdapter(this, R.layout.row, list)
                }
            }
        })

        calendar = Calendar.getInstance();
        val today = Date();
        calendar.set(Calendar.MONTH, today.month);
        calendar.set(Calendar.DAY_OF_MONTH, today.day + 5);
        calendar.set(Calendar.YEAR, today.year + 1900);

        viewModel.db.getEventByDay(today.year, today.month, today.day)

        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            calendar.set(year, month, day)
            viewModel.db.getEventByDay(year, month, day)
        }
    }


    fun addNewEvent(view: View) {
        val intent = Intent(applicationContext, AddEventActivity::class.java)

        //   intent.putExtra("input", editEventName.text)
        startActivity(intent)

    }
}
