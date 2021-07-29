package com.app.letschat.ui.register

import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R

class RegisterActivity:AppCompatActivity() {
    lateinit var tv_policy:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        tv_policy=findViewById(R.id.tv_policy)
        val text = "<font color=#000000>By signing up I agree to the</font> <font color=#22B0FC>Privacy Policy</font><font color=#000000>and</font><font color=#22B0FC>Terms & conditions</font>"
        tv_policy.setText(Html.fromHtml(text))

    }
}