package com.example.commonground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.commonground.ui.main.MainViewModel
import com.google.firebase.Timestamp
import kotlinx.android.synthetic.main.activity_add_event.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.btnAddEvent
import kotlinx.android.synthetic.main.main_fragment.editEventDate
import kotlinx.android.synthetic.main.main_fragment.editEventName
import java.util.*


class AddEventActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    companion object {
        fun newInstance() = AddEventActivity()
    }


  /*  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.activity_add_event, container, false)
    }
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

      val ACTION_BAR = supportActionBar
      //set actionbar title
      ACTION_BAR!!.title = "Add Event"


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.events.observe(this, Observer {
                events -> txtEventName.text = events.get(0).eventName
        })
        btnAddEvent.setOnClickListener(){
            viewModel.db.addEvent(editEventName.text.toString(), Timestamp(Date(editEventDate.text.toString())))
            editEventName.text.clear()
            editEventDate.text.clear()
            editEventLocation.text.clear()
        }
    }

}
