package com.app.letschat.ui.startmetting

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R

class Startmeeting:AppCompatActivity() {
    lateinit var btn_start_meeting:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_meeting)
        btn_start_meeting=findViewById(R.id.btn_start_meeting)
        btn_start_meeting.setOnClickListener{
            val i=Intent(this,Endmeeting::class.java)
            startActivity(i)
        }
    }
}