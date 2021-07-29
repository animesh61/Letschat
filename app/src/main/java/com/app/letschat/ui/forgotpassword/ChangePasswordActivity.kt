package com.app.letschat.ui.forgotpassword

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R

class ChangePasswordActivity:AppCompatActivity() {
    lateinit var btn_submit:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changepassword)
        btn_submit=findViewById(R.id.btn_submit)
        btn_submit.setOnClickListener{
            val changepasswordDialog=ChangepasswordDialog(this)
            changepasswordDialog.show()
        }
    }
}