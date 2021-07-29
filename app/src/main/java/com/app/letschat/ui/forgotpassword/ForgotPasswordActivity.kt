package com.app.letschat.ui.forgotpassword

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R

class ForgotPasswordActivity:AppCompatActivity() {
    lateinit var btn_send:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        btn_send=findViewById(R.id.btn_send)
        btn_send.setOnClickListener{
           val i=Intent(this,EmailActivation::class.java)
            startActivity(i)
        }
    }
}