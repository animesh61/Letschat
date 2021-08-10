package com.app.letschat.ui.forgotpassword

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
import com.app.letschat.model.ChangepasswordRequest
import com.app.letschat.ui.login.LoginActivity
import com.app.letschat.utils.ViewModelFactory
import com.example.akaya.utils.AndroidUtility
import com.example.akaya.utils.Prefs
import com.example.akaya.utils.Status

class ChangePasswordActivity:AppCompatActivity() {
    lateinit var btn_submit:Button
    lateinit var et_password:EditText
    lateinit var et_confirmpassword:EditText
     var otp:String?=null
    private lateinit var viewModel: Changepasswordviewmodel
    lateinit var mCustomLoaderDialog: CustomLoaderDialog
    var email1:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changepassword)
        btn_submit=findViewById(R.id.btn_submit)
        et_password=findViewById(R.id.et_password)
        et_confirmpassword=findViewById(R.id.et_confirmpassword)
        otp=intent.getStringExtra("otp")
        email1 = Prefs.with(this).read("email")

        mCustomLoaderDialog = CustomLoaderDialog(this)

        setUpViewModel()
        setupObserver()

        btn_submit.setOnClickListener{
            changePasswordApi()
//            val changepasswordDialog=ChangepasswordDialog(this)
//            changepasswordDialog.show()
        }
    }

    fun showLoader() {
        mCustomLoaderDialog.show()
    }

    fun hideLoader() {
        if (mCustomLoaderDialog.isShowing)
            mCustomLoaderDialog.cancel()
    }

    private fun changePasswordApi(){
        val password=et_password.text.toString().trim()
        val conpassword=et_confirmpassword.text.toString().trim()

        when {
            (!AndroidUtility.isNetworkAvailable(this)) -> {
                AndroidUtility.showToast(this, getString(R.string.please_check_internet))
                return
            }
            password == "" -> {
                AndroidUtility.showToast(this, "Password can't be blank")
                return

            }
            conpassword ==""->{
                AndroidUtility.showToast(this, "Confirm Password can't be blank")
                return
            }
            password!=conpassword->{
                AndroidUtility.showToast(this, "Password didn't match")
                return

            }

        }

        val changePasswordRequest = ChangepasswordRequest().apply {

            this.source = "MOB"
            this.email=email1
            this.password=password
            this.reset_password_otp=otp
        }

        viewModel.requestchangepassword(this,changePasswordRequest)


    }

    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        ).get(Changepasswordviewmodel::class.java)
    }
    private fun setupObserver() {
        viewModel.changepasswordLivedata().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    hideLoader()
                    val baseResponse = it.data
                    val errorCode = baseResponse?.status?.error_code1

                    when {
                        (errorCode == 0) -> {
                            AndroidUtility.showToast(this, "Password Changed Successfully!!!")
                            val intent=Intent(this,LoginActivity::class.java)
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