package com.app.letschat.ui.forgotpassword

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R
import com.app.letschat.ui.login.LoginActivity

class ChangepasswordDialog(context: Context): Dialog(context) {
    lateinit var tv_ok:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_change_password)
        tv_ok=findViewById(R.id.tv_ok)
        setCanceledOnTouchOutside(true)
        setCancelable(true)
        window!!.setGravity(Gravity.CENTER);
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val wmlp = window!!.attributes
        wmlp.width = ViewGroup.LayoutParams.MATCH_PARENT
        wmlp.height = ViewGroup.LayoutParams.WRAP_CONTENT
        // wmlp.windowAnimations = R.style.DialogAnimation

        tv_ok.setOnClickListener {
            val i=Intent(context,LoginActivity::class.java)
            context.startActivity(i)
        }
    }

}