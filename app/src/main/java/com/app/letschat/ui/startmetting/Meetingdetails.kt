package com.app.letschat.ui.startmetting

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R

class Meetingdetails:AppCompatActivity() {
    lateinit var tv_delete:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meeting_details)
        tv_delete=findViewById(R.id.tv_delete)
        tv_delete.setOnClickListener({
            val deletemeetingDialog=DeletemeetingDialog(this)
            deletemeetingDialog.show()
        })
    }
}