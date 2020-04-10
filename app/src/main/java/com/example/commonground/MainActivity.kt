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
            events -> events.forEach {
            list.add(it)
            listView.adapter = MyAdapter(this, R.layout.row, list)

        }
        })

        calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 9);
        calendar.set(Calendar.YEAR, 2012);


        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.YEAR, 1);


        calendarView = findViewById(R.id.calendarView);


        calendarView.setOnDateChangeListener { calendarView, i, i1, i2 ->
            val msg =
                "Selected date Day: " + i2 + " Month : " + (i1 + 1) + " Year " + i
            Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
        }
    }


    fun addNewEvent(view: View) {
        val intent = Intent(applicationContext, AddEventActivity::class.java)

        //   intent.putExtra("input", editEventName.text)
        startActivity(intent)

    }
}
