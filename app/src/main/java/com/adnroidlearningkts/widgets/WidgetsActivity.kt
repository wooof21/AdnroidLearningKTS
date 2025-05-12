package com.adnroidlearningkts.widgets

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adnroidlearningkts.R
import java.util.Locale

class WidgetsActivity : AppCompatActivity() {

    private lateinit var checkbox: CheckBox
    private lateinit var radioGroup: RadioGroup
    private lateinit var spinner: Spinner
    private lateinit var timePicker: TimePicker


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_widgets)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initCheckBox()

        initRadioBtn()

        initSpinner()

        initTimePicker()
    }

    private fun initCheckBox() {
        checkbox = findViewById(R.id.widgets_checkbox)
        /**
         * Checkbox state change
         *
         * buttonView: CompoundButton -> represents the "CompoundButton" that has
         * had checked state changed, refers to the CheckBox
         *
         */
        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            when(isChecked) {
                true -> Toast.makeText(this, "CheckBox Checked", Toast.LENGTH_SHORT).show()
                false -> Toast.makeText(this, "CheckBox Unchecked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initRadioBtn() {
        radioGroup = findViewById(R.id.widgets_radio_group)

        /**
         * RadioGroup: ensure only 1 option can be selected at a time
         *
         * radioGroup: refer to the radioGroup itself
         * i: represent the resource ID of checked RadioButton
         */
        radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            when(i) {
                R.id.widgets_radio_btn1 -> Toast.makeText(this, "Option1 Selected", Toast.LENGTH_SHORT).show()
                R.id.widgets_radio_btn2 -> Toast.makeText(this, "Option2 Selected", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initSpinner() {
        /**
         * Spinner: provides a dorp-down menu with a list of items where user can select on item
         */
        spinner = findViewById(R.id.widget_spinner)

        //Data Sources
        val os = arrayOf("Windows", "Android", "Linux", "iOS")

        //Link View with Data Sources - Adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, os)

        //Link Adapter to View
        spinner.adapter = adapter

        /**
         * Handle Spinner Item Selection
         *
         * object: is a way to create an anonymous object in Kotlin
         * AdapterView.OnItemSelectedListener: is the interface that need to be implemented
         *
         * Kotlin lambdas can be used as concise syntax for interfaces that have a single abstract method (SAM).
         * This is often referred to as SAM conversion.
         * If an interface has more than one method, a lambda cannot be used directly
         * because the compiler wouldn't know which lambda function corresponds to which interface method.
         * Because AdapterView.OnItemSelectedListener has two methods,
         * it cannot be represented by a single lambda.
         * Therefore, you must create an anonymous object (using object : ...)
         * that explicitly implements the interface and provides implementations
         * for both onItemSelected and onNothingSelected.
         *
         *  p0 -> parent view - spinner
         *  p1 -> selected item view
         *  p2 -> position - position of selected item
         *  p3 -> id - row id of selected item, typically the same as position
         */
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                val selectedItem = p0?.getItemAtPosition(p2).toString()
                Toast.makeText(applicationContext, "Selected Item: $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun initTimePicker() {
        timePicker = findViewById(R.id.widgets_time_picker)

        /**
         * Handle changes in time
         *
         * timePicker: represent the View - TimePicker itself
         * i: hourOfDay - selected hour in 24-hour format, range 0 - 23
         * i2: minute - selected minute, range 0 - 59
         */
        timePicker.setOnTimeChangedListener { timePicker, i, i2 ->

            //format the time - with 2 digits padding with 0 if necessary
            val selectedTime = String.format(Locale.getDefault(), "%02d:%02d", i, i2)
            Toast.makeText(this, "Selected Time: $selectedTime", Toast.LENGTH_SHORT).show()
        }
    }
}