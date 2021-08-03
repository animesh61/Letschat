package com.app.letschat.ui.startmetting

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R

class Endmeeting:AppCompatActivity() {
    lateinit var btn_endmeeting:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_meeting)
        btn_endmeeting=findViewById(R.id.btn_endmeeting)

        btn_endmeeting.setOnClickListener{
            val i= Intent(this,JoinMeeting::class.java)
            startActivity(i)

        }
    }
}