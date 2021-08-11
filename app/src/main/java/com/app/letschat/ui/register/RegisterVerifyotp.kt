package com.app.letschat.ui.register

import android.content.Intent
import android.os.Bundle
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
import com.app.letschat.model.SignupRequest
import com.app.letschat.model.verifyregisterRequest
import com.app.letschat.ui.forgotpassword.ChangePasswordActivity
import com.app.letschat.ui.login.LoginActivity
import com.app.letschat.utils.ViewModelFactory
import com.example.akaya.utils.AndroidUtility
import com.example.akaya.utils.Prefs
import com.example.akaya.utils.Status

class RegisterVerifyotp:AppCompatActivity() {
    lateinit var et_otp: EditText
    lateinit var et_otp1: EditText
    lateinit var et_otp2: EditText
    lateinit var et_otp3: EditText
    lateinit var et_otp4: EditText
    lateinit var et_otp5: EditText
    lateinit var btn_verify: Button
    private lateinit var viewModel: Verifyotpmodel
    lateinit var mCustomLoaderDialog: CustomLoaderDialog
    var email1:String?=null

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
        mCustomLoaderDialog = CustomLoaderDialog(this)
        email1 = Prefs.with(this).read("email")


        setUpViewModel()

        setupObserver()


        btn_verify.setOnClickListener{
            val otp_value=et_otp.text.toString().trim()
            val otp_value1=et_otp1.text.toString().trim()
            val otp_value2=et_otp2.text.toString().trim()
            val otp_value3=et_otp3.text.toString().trim()
            val otp_value4=et_otp4.text.toString().trim()
            val otp_value5=et_otp5.text.toString().trim()

            val otp_user=otp_value+otp_value1+otp_value2+otp_value3+otp_value4+otp_value5

            val verifyrequest = verifyregisterRequest().apply {
                this.email=email1
                this.otp=otp_user
            }

            viewModel.requestverifyotp(this, verifyrequest)

        }




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
        ).get(Verifyotpmodel::class.java)
    }
    private fun setupObserver() {
        viewModel.verifyotplivedata().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    hideLoader()
                    val baseResponse = it.data
                    val errorCode = baseResponse?.status?.error_code4
                    when {
                        (errorCode == 0) -> {
                            AndroidUtility.showToast(this, "Register Successfully !!")
                            val intent= Intent(this, LoginActivity::class.java)
                            startActivity(intent)


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
}