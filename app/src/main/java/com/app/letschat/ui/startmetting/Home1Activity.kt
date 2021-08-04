package com.app.letschat.ui.startmetting

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R

class Home1Activity:AppCompatActivity() {
    lateinit var tv_start:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home1)
        tv_start=findViewById(R.id.tv_start)
        tv_start.setOnClickListener({
            val i=Intent(this,Meeting::class.java)
            startActivity(i)
        })
    }
}