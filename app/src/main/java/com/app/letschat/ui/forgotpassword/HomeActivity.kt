package com.app.letschat.ui.forgotpassword

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R
import com.app.letschat.ui.Contact
import com.app.letschat.ui.profile.Settings
import com.app.letschat.ui.startmetting.JoinMeeting
import com.app.letschat.ui.startmetting.Meeting
import com.app.letschat.ui.startmetting.ScheduleMeeting
import com.app.letschat.ui.startmetting.Startmeeting

class HomeActivity:AppCompatActivity() {
    lateinit var ll_meeting:LinearLayout
    lateinit var ll_contacts:LinearLayout
    lateinit var ll_setings:LinearLayout
    lateinit var ll_join_meeting:LinearLayout
    lateinit var ll_schedule:LinearLayout
    lateinit var ll_new_meeting:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ll_meeting=findViewById(R.id.ll_meeting)
        ll_contacts=findViewById(R.id.ll_contacts)
        ll_setings=findViewById(R.id.ll_setings)
        ll_join_meeting=findViewById(R.id.ll_join_meeting)
        ll_schedule=findViewById(R.id.ll_schedule)
        ll_new_meeting=findViewById(R.id.ll_new_meeting)
        ll_meeting.setOnClickListener({
            val i=Intent(this,Meeting::class.java)
            startActivity(i)
        })
        ll_contacts.setOnClickListener({
            val i=Intent(this,Contact::class.java)
            startActivity(i)
        })
        ll_setings.setOnClickListener({
            val i=Intent(this,Settings::class.java)
            startActivity(i)
        })
        ll_join_meeting.setOnClickListener({
            val i=Intent(this,JoinMeeting::class.java)
            startActivity(i)
        })
        ll_schedule.setOnClickListener({
            val i=Intent(this,ScheduleMeeting::class.java)
            startActivity(i)
        })
        ll_new_meeting.setOnClickListener({
            val i=Intent(this,Startmeeting::class.java)
            startActivity(i)
        })



    }
}