package com.example.wheelsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class NotificationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener OnNavigationItemSelectedListener@{ item ->
            when (item.itemId) {
                R.id.Movies -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.Series -> {
                    val intent1 = Intent(this, SeriesActivity::class.java)
                    startActivity(intent1)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.Explore -> {
                    val intent2 = Intent(this, ExploreActivity::class.java)
                    startActivity(intent2)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.Notifications -> {

                    return@OnNavigationItemSelectedListener true
                }
            }
            return@OnNavigationItemSelectedListener true
        }
    }
}