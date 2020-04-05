package com.example.commonground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.commonground.ui.main.MainViewModel
import com.google.firebase.Timestamp
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*


class AddEventActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

      val actionBar = supportActionBar
      actionBar!!.title = "Add Event"
      actionBar.setDisplayHomeAsUpEnabled(true)


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.events.observe(this, Observer {
                events -> txtEventName.text = events[0].eventName
        })
        this.btnAddEvent.setOnClickListener(){
            viewModel.db.addEvent(editEventName.text.toString(), Timestamp(Date(editEventDate.text.toString())))
            editEventName.text.clear()
            editEventDate.text.clear()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
