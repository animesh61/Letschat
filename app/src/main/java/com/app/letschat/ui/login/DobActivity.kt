package com.app.letschat.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R
import com.app.letschat.ui.forgotpassword.HomeActivity

class DobActivity:AppCompatActivity() {
    lateinit var btn_continue:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dob)
        btn_continue=findViewById(R.id.btn_continue)
        btn_continue.setOnClickListener({
            val i=Intent(this,HomeActivity::class.java)
            startActivity(i)
        })
    }
}