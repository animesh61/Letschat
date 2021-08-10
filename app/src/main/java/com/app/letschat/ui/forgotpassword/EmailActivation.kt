package com.app.letschat.ui.forgotpassword

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R

class EmailActivation:AppCompatActivity() {
    lateinit var btn_verify:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)
        btn_verify=findViewById(R.id.btn_verify)
        val otp=intent.getStringExtra("otp")

        btn_verify.setOnClickListener{
            val intent=Intent(this,Verifyotp::class.java)
            intent.putExtra("otp",otp)
            startActivity(intent)
        }
    }
}