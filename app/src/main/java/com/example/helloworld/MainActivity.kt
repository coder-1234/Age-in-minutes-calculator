package com.example.helloworld

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDate :TextView? = null
    private var minutes: TextView? = null

    private fun clickDatePicker(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,{ _, selectedYear, selectedMonth, dayOfMonth ->
            val displayText = "$dayOfMonth-${selectedMonth+1}-$selectedYear"
            selectedDate?.text = displayText
            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
            val actualDate = sdf.parse(displayText)
            actualDate?.let{
                val min = (actualDate.time /60000)
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currInMin = currentDate.time/60000
                    val diff = currInMin - min
                    minutes?.text = diff.toString()
                }
            }


        }, year, month, day)
        dpd.datePicker.maxDate = System.currentTimeMillis()-86400000
        dpd.show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectedDate = findViewById(R.id.textView4)
        minutes = findViewById(R.id.textView6)
        val btnDatePicker: Button = findViewById(R.id.buttonDatePicker)
        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }
    }
}