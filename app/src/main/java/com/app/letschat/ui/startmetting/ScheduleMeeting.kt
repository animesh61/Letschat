package com.app.letschat.ui.startmetting

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R
import com.app.letschat.ui.Contact

class ScheduleMeeting:AppCompatActivity() {
    lateinit var ll_date:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_meeting)
        ll_date=findViewById(R.id.ll_date)
        ll_date.setOnClickListener({
            val i=Intent(this,Contact::class.java)
            startActivity(i)
        })
    }
}