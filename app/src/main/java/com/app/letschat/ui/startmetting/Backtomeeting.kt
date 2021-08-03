package com.app.letschat.ui.startmetting

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R

class Backtomeeting:AppCompatActivity() {
    lateinit var ll_schedule:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back_to_meeting)
        ll_schedule=findViewById(R.id.ll_schedule)
        ll_schedule.setOnClickListener({
            val i=Intent(this,ScheduleMeeting::class.java)
            startActivity(i)
        })
    }
}