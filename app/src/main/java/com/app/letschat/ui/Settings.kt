package com.app.letschat.ui

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R
import com.app.letschat.ui.forgotpassword.HomeActivity
import com.app.letschat.ui.startmetting.Meeting

class Settings:AppCompatActivity() {
    lateinit var ll_home:LinearLayout
    lateinit var ll_meeting:LinearLayout
    lateinit var ll_contacts:LinearLayout
    lateinit var ll_seetings:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seetings)
        ll_home=findViewById(R.id.ll_home)
        ll_meeting=findViewById(R.id.ll_meeting)
        ll_contacts=findViewById(R.id.ll_contacts)
        ll_seetings=findViewById(R.id.ll_seetings)
        ll_home.setOnClickListener({
            val i= Intent(this, HomeActivity::class.java)
            startActivity(i)
        })
        ll_meeting.setOnClickListener({
            val i= Intent(this, Meeting::class.java)
            startActivity(i)
        })
        ll_contacts.setOnClickListener({
            val i= Intent(this,Contact::class.java)
            startActivity(i)
        })
        ll_seetings.setOnClickListener({
            val i= Intent(this,Settings::class.java)
            startActivity(i)
        })


    }
}