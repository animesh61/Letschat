package com.app.letschat.ui.forgotpassword

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R
import com.example.akaya.utils.AndroidUtility

class Verifyotp:AppCompatActivity() {
    lateinit var et_otp:EditText
    lateinit var et_otp1:EditText
    lateinit var et_otp2:EditText
    lateinit var et_otp3:EditText
    lateinit var et_otp4:EditText
    lateinit var et_otp5:EditText
    lateinit var btn_verify:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otp)
        et_otp=findViewById(R.id.et_otp)
        et_otp1=findViewById(R.id.et_otp1)
        et_otp2=findViewById(R.id.et_otp2)
        et_otp3=findViewById(R.id.et_otp3)
        et_otp4=findViewById(R.id.et_otp4)
        et_otp5=findViewById(R.id.et_otp5)
        btn_verify=findViewById(R.id.btn_verify)
        val otp=intent.getStringExtra("otp")


        btn_verify.setOnClickListener{
            val otp_value=et_otp.text.toString().trim()
            val otp_value1=et_otp1.text.toString().trim()
            val otp_value2=et_otp2.text.toString().trim()
            val otp_value3=et_otp3.text.toString().trim()
            val otp_value4=et_otp4.text.toString().trim()
            val otp_value5=et_otp5.text.toString().trim()

            val otp_user=otp_value+otp_value1+otp_value2+otp_value3+otp_value4+otp_value5
            Log.e("otp",otp.toString())
            Log.e("otp_user",otp_user.toString())

            if(otp.equals(otp_user)) {
                val i = Intent(this, ChangePasswordActivity::class.java)
                i.putExtra("otp",otp)
                startActivity(i)
            }
            else{
                AndroidUtility.showToast(this,"Otp didn't match")
            }
        }


    }
}