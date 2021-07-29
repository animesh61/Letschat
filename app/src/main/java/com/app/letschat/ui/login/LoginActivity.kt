package com.app.letschat.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R
import com.app.letschat.ui.forgotpassword.ForgotPasswordActivity
import com.app.letschat.ui.register.RegisterActivity


class LoginActivity:AppCompatActivity() {
    lateinit var tv_create:TextView
    lateinit var btn_signin:Button
    lateinit var tv_forgotpassword:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        tv_create=findViewById(R.id.tv_create)
        val text = "<font color=#1D293F>New here?</font> <font color=#22B0FC><u>Create a new One</u></font>"
        tv_create.setText(Html.fromHtml(text))
        btn_signin=findViewById(R.id.btn_signin)
        tv_forgotpassword=findViewById(R.id.tv_forgotpassword)
        btn_signin.setOnClickListener{
          val intent=Intent(this,DobActivity::class.java)
          startActivity(intent)
        }
        tv_forgotpassword.setOnClickListener{
            val intent=Intent(this,ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
        tv_create.setOnClickListener({
            val intent=Intent(this,RegisterActivity::class.java)
            startActivity(intent)

        })

    }
}