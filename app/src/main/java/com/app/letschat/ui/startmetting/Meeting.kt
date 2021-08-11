package com.app.letschat.ui.startmetting

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R
import com.app.letschat.ui.Contact
import com.app.letschat.ui.forgotpassword.HomeActivity
import com.app.letschat.ui.profile.Settings

class Meeting:AppCompatActivity() {
    lateinit var ll_home:LinearLayout
    lateinit var ll_meetings:LinearLayout
    lateinit var ll_contacts:LinearLayout
    lateinit var ll_settings:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meeting)
        ll_home=findViewById(R.id.ll_home)
        ll_meetings=findViewById(R.id.ll_meetings)
        ll_contacts=findViewById(R.id.ll_contacts)
        ll_settings=findViewById(R.id.ll_settings)
        ll_home.setOnClickListener({
            val intent=Intent(this,HomeActivity::class.java)
            startActivity(intent)
        })
        ll_meetings.setOnClickListener({
            val intent=Intent(this,Meeting::class.java)
            startActivity(intent)
        })
        ll_contacts.setOnClickListener({
            val intent=Intent(this,Contact::class.java)
            startActivity(intent)
        })
        ll_settings.setOnClickListener({
            val intent=Intent(this,Settings::class.java)
            startActivity(intent)
        })



    }
}