package com.example.commonground

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.commonground.DTO.CalendarEvent
import com.example.commonground.DTO.MyAdapter
import org.hamcrest.CoreMatchers.anything
import org.hamcrest.CoreMatchers.startsWith

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {


    @Rule
    @JvmField
    var rule: ActivityTestRule<AddEventActivity> = ActivityTestRule(AddEventActivity::class.java)
    var mMainActivity: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.commonground", appContext.packageName)
    }

/*
    @Test
    fun IsDataExistingInListView() {
        onView(withId(R.id.editEventName)).perform(typeText("Pick up kids"))
        onView(withId(R.id.editEventDate)).perform(typeText("4/20/2020"))

        onData(anything())
            .inAdapterView(withId(R.id.main_listview))
            .atPosition(0).onChildView(withId(R.id.main_listview))
            .check(matches(withText(startsWith("Pick"))))
    }
*/

    // Whenever we try to type in the word for the txtEventName ex: "Pick up kids", it will pull it from Add Event activity. The rule allows it to pull from that certain activity..
    @Test
    fun userCanEnterInAEventTitle() {
        onView(withId(R.id.editEventName)).perform(typeText("Pick up kids"))
    }
    @Test
    fun userCanEnterInAEventDate() {
        onView(withId(R.id.editEventDate)).perform(typeText("5/5/20"))
    }


}
