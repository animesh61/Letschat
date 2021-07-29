package com.app.letschat.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R
import com.app.letschat.ui.login.LoginActivity
import com.app.letschat.ui.register.RegisterActivity

class LandingActivity:AppCompatActivity() {
    lateinit var btn_signin:Button
    lateinit var btnsignup:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        btn_signin=findViewById(R.id.btn_signin)
        btnsignup=findViewById(R.id.btnsignup)
        btn_signin.setOnClickListener{
            val i=Intent(this,LoginActivity::class.java)
            startActivity(i)
        }
        btnsignup.setOnClickListener{
            val i=Intent(this,RegisterActivity::class.java)
            startActivity(i)
        }

    }
}