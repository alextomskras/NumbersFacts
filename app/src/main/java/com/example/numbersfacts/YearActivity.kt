package com.example.numbersfacts

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText

class YearActivity : AppCompatActivity() {
    private lateinit var textMessage: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_year)
//        setSupportActionBar(null)
//        main_editText!!.visibility = View.GONE
//        main_yearText!!.visibility = View.VISIBLE
    }
}
