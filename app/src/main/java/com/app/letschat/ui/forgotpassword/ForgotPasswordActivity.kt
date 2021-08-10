package com.app.letschat.ui.forgotpassword

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.letschat.R
import com.app.letschat.api.ApiHelperImpl
import com.app.letschat.api.RetrofitBuilder
import com.app.letschat.dialog.CustomLoaderDialog
import com.app.letschat.model.ForgotpasswordRequest
import com.app.letschat.model.SigninRequest
import com.app.letschat.ui.login.Loginviewmodel
import com.app.letschat.utils.ViewModelFactory
import com.example.akaya.utils.AndroidUtility
import com.example.akaya.utils.Prefs
import com.example.akaya.utils.Status

class ForgotPasswordActivity:AppCompatActivity() {
    lateinit var btn_send:Button
    lateinit var et_forgot_email:EditText
    private lateinit var viewModel: ForgotpasswordViewmodel
    lateinit var mCustomLoaderDialog: CustomLoaderDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        et_forgot_email=findViewById(R.id.et_forgot_email)
        btn_send=findViewById(R.id.btn_send)
        mCustomLoaderDialog = CustomLoaderDialog(this)

        setUpViewModel()
        setupObserver()

        btn_send.setOnClickListener {
//            val i = Intent(this, EmailActivation::class.java)
//            startActivity(i)
            forgotpasswordApi()
        }


    }

    private fun movetoemailActivitation(){
        val i=Intent(this,EmailActivation::class.java)
        startActivity(i)
    }

    fun showLoader() {
        mCustomLoaderDialog.show()
    }

    fun hideLoader() {
        if (mCustomLoaderDialog.isShowing)
            mCustomLoaderDialog.cancel()
    }
    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        ).get(ForgotpasswordViewmodel::class.java)
    }
    private fun setupObserver() {
        viewModel.forgotpasswordlivedata().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    hideLoader()
                    val baseResponse = it.data
                    val errorCode = baseResponse?.status?.error_code
                    val otp=baseResponse?.result.toString()

                    when {
                        (errorCode == 0) -> {
                            AndroidUtility.showToast(this, baseResponse.status?.message?:"")
                            val i=Intent(this,EmailActivation::class.java)
                            i.putExtra("otp",otp)
                            Prefs.with(this).write("email", et_forgot_email.text.toString().trim())

                            Log.e("forgot_otp", otp)

                            startActivity(i)


                        }
                        (errorCode == 1) -> {
                            AndroidUtility.showToast(this, baseResponse.status?.message ?: "")
                        }
                    }


                }


                Status.LOADING -> {
                    showLoader()
                }
                Status.ERROR -> {
                    hideLoader()
                    AndroidUtility.showToast(this, getString(R.string.something_went_wrong))
                }
            }

        })
    }

    private fun forgotpasswordApi(){
        val email=et_forgot_email.text.toString().trim()


        when {
            (!AndroidUtility.isNetworkAvailable(this)) -> {
                AndroidUtility.showToast(this, getString(R.string.please_check_internet))
                return
            }
            email == "" -> {
                AndroidUtility.showToast(this, "Email can't be blank")
                return
            }
            (!AndroidUtility.isValidEmail(email)) -> {
                AndroidUtility.showToast(this, "Please enter a valid email.")
                return
            }

        }

        val forgotpasswordRequest = ForgotpasswordRequest().apply {

            this.source="MOB"
            this.email = email

        }

        viewModel.requestforgotpassword(this, forgotpasswordRequest)




    }

}