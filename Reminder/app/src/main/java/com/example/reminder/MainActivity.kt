package com.example.reminder

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TimePicker
import com.example.reminder.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val TAG = "setTime"
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)
    }

    fun onSetTimeButtonClicked(view: View) {
        val calender : Calendar = Calendar.getInstance()
        val timePickerListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            calender.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calender.set(Calendar.MINUTE, minute)
            Log.d(TAG, "Jam: ${calender.get(Calendar.HOUR_OF_DAY)} " +
                    "Menit: ${calender.get(Calendar.MINUTE)}")
        }
        TimePickerDialog(this, timePickerListener, calender.get(Calendar.HOUR_OF_DAY),
            calender.get(Calendar.MINUTE),
            true
            ).show()
    }
}