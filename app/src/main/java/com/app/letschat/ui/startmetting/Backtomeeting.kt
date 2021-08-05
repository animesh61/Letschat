package com.app.letschat.ui.startmetting

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R
import com.app.letschat.ui.Contact
import com.app.letschat.ui.Settings
import com.app.letschat.ui.forgotpassword.HomeActivity

class Backtomeeting:AppCompatActivity() {
    lateinit var ll_schedule:LinearLayout
    lateinit var ll_back_to_meeting:LinearLayout
    lateinit var ll_home:LinearLayout
    lateinit var ll_meeting:LinearLayout
    lateinit var ll_settings:LinearLayout
    lateinit var ll_contact:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back_to_meeting)
        ll_schedule=findViewById(R.id.ll_schedule)
        ll_back_to_meeting=findViewById(R.id.ll_back_to_meeting)
        ll_home=findViewById(R.id.ll_home)
        ll_meeting=findViewById(R.id.ll_meeting)
        ll_contact=findViewById(R.id.ll_contact)
        ll_settings=findViewById(R.id.ll_settings)
        ll_schedule.setOnClickListener({
            val i=Intent(this,ScheduleMeeting::class.java)
            startActivity(i)
        })
        ll_back_to_meeting.setOnClickListener({
            val i=Intent(this,Meetingdetails::class.java)
            startActivity(i)
        })
        ll_home.setOnClickListener({
            val i=Intent(this,HomeActivity::class.java)
            startActivity(i)
        })
        ll_meeting.setOnClickListener({
            val i=Intent(this,Meeting::class.java)
            startActivity(i)
        })
        ll_contact.setOnClickListener({
            val i=Intent(this,Contact::class.java)
            startActivity(i)
        })
        ll_settings.setOnClickListener({
            val i=Intent(this,Settings::class.java)
            startActivity(i)
        })

    }
}