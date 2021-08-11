package com.app.letschat.ui.startmetting

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R
import com.app.letschat.ui.Contact
import com.app.letschat.ui.forgotpassword.HomeActivity
import com.app.letschat.ui.profile.Settings

class Home1Activity:AppCompatActivity() {
    lateinit var tv_start:TextView
    lateinit var ll_home:LinearLayout
    lateinit var ll_meeting:LinearLayout
    lateinit var ll_contacts:LinearLayout
    lateinit var ll_seetings:LinearLayout
    lateinit var ll_new_meeting:LinearLayout
    lateinit var ll_join_meeting:LinearLayout
    lateinit var ll_schedule:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home1)
        ll_home=findViewById(R.id.ll_home)
        ll_meeting=findViewById(R.id.ll_meeting)
        ll_contacts=findViewById(R.id.ll_contacts)
        ll_seetings=findViewById(R.id.ll_seetings)
        tv_start=findViewById(R.id.tv_start)
        ll_new_meeting=findViewById(R.id.ll_new_meeting)
        ll_join_meeting=findViewById(R.id.ll_join_meeting)
        ll_schedule=findViewById(R.id.ll_schedule)
//        tv_start.setOnClickListener({
//            val i=Intent(this,Meeting::class.java)
//            startActivity(i)
//        })
        ll_home.setOnClickListener({
            val i=Intent(this,HomeActivity::class.java)
            startActivity(i)
        })
        ll_meeting.setOnClickListener({
            val i=Intent(this,Meeting::class.java)
            startActivity(i)
        })
        ll_contacts.setOnClickListener({
            val i=Intent(this,Contact::class.java)
            startActivity(i)
        })
        ll_seetings.setOnClickListener({
            val i=Intent(this,Settings::class.java)
            startActivity(i)
        })
        ll_new_meeting.setOnClickListener({
            val i=Intent(this,Startmeeting::class.java)
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



    }
}