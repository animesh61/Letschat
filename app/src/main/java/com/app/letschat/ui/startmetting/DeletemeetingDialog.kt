package com.app.letschat.ui.startmetting

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.letschat.R
import com.app.letschat.ui.login.LoginActivity

class DeletemeetingDialog(context: Context): Dialog(context) {
    lateinit var tv_cancel: TextView
    lateinit var tv_delete:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_delete_meeting)
        tv_cancel = findViewById(R.id.tv_cancel)
        tv_delete=findViewById(R.id.tv_delete)
        setCanceledOnTouchOutside(true)
        setCancelable(true)
        window!!.setGravity(Gravity.CENTER);
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val wmlp = window!!.attributes
        wmlp.width = ViewGroup.LayoutParams.MATCH_PARENT
        wmlp.height = ViewGroup.LayoutParams.WRAP_CONTENT
        // wmlp.windowAnimations = R.style.DialogAnimation

        tv_cancel.setOnClickListener {
            dismiss()
        }
        tv_delete.setOnClickListener {
            val i=Intent(context,Home1Activity::class.java)
            context.startActivity(i)
        }

    }
}