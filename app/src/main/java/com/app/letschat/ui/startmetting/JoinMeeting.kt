package com.app.letschat.ui.startmetting

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R

class JoinMeeting:AppCompatActivity() {
    lateinit var btn_join_meeting:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_meeting)
        btn_join_meeting=findViewById(R.id.btn_join_meeting)
        btn_join_meeting.setOnClickListener{
           val i=Intent(this,Backtomeeting::class.java)
            startActivity(i)
        }
    }
}