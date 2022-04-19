package com.example.shop_book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        Timer().schedule(timerTask {
            val intent= Intent(this@MainActivity,Homeactivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}