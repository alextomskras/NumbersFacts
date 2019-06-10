package com.example.numbersfacts

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast

class BottomActivity : AppCompatActivity() {

    lateinit var textMessage: TextView
    val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                textMessage.setText(R.string.title_home)
                Toast.makeText(this@BottomActivity, "HOME...", Toast.LENGTH_SHORT).show()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                textMessage.setText(R.string.title_dashboard)
//                main_editText.
                Toast.makeText(this@BottomActivity, "Dashboard...", Toast.LENGTH_SHORT).show()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                textMessage.setText(R.string.title_notifications)
                Toast.makeText(this@BottomActivity, "Notification...", Toast.LENGTH_SHORT).show()
//                main_yearText.visibility = View.VISIBLE
//                val intent = Intent(this, YearActivity::class.java)
//
//                startActivity(intent)
                startActivity(Intent(this, YearActivity::class.java))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        textMessage = findViewById(R.id.bottomMessage)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
