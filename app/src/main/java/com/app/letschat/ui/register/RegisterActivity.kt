package com.app.letschat.ui.register

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.text.Html
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.letschat.R
import com.app.letschat.api.ApiHelperImpl
import com.app.letschat.api.RetrofitBuilder
import com.app.letschat.dialog.CustomLoaderDialog
import com.app.letschat.model.SigninRequest
import com.app.letschat.model.SignupRequest
import com.app.letschat.ui.forgotpassword.HomeActivity
import com.app.letschat.ui.login.Loginviewmodel
import com.app.letschat.utils.ViewModelFactory
import com.example.akaya.utils.AndroidUtility
import com.example.akaya.utils.Status

class RegisterActivity:AppCompatActivity() {
    lateinit var tv_policy:TextView
    lateinit var et_first_name:EditText
    lateinit var et_lname:EditText
    lateinit var et_email:EditText
    lateinit var et_password:EditText
    lateinit var btn_sign_up:Button
    private lateinit var viewModel: RegisterViewmodel
    lateinit var mCustomLoaderDialog: CustomLoaderDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        tv_policy=findViewById(R.id.tv_policy)
        val text = "<font color=#000000>By signing up I agree to the</font> <font color=#22B0FC>Privacy Policy</font><font color=#000000>and</font><font color=#22B0FC>Terms & conditions</font>"
        tv_policy.setText(Html.fromHtml(text))
        et_first_name=findViewById(R.id.et_first_name)
        et_lname=findViewById(R.id.et_lname)
        et_email=findViewById(R.id.et_email)
        et_password=findViewById(R.id.et_password)
        btn_sign_up=findViewById(R.id.btn_sign_up)
        mCustomLoaderDialog = CustomLoaderDialog(this)

        setUpViewModel()

        setupObserver()
        btn_sign_up.setOnClickListener{
           registerApi()
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
        ).get(RegisterViewmodel::class.java)
    }
    private fun setupObserver() {
        viewModel.signuplivedata().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    hideLoader()
                    val baseResponse = it.data
                    val errorCode = baseResponse?.status?.error_code3
                    when {
                        (errorCode == 0) -> {
                            AndroidUtility.showToast(this, "Register Successfully !!")
                            val intent= Intent(this, HomeActivity::class.java)
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

    private fun registerApi(){
        val fname=et_first_name.text.toString().trim()
        val lname=et_lname.text.toString().trim()
        val email=et_email.text.toString().trim()
        val password=et_password.text.toString().trim()


        when {
            (!AndroidUtility.isNetworkAvailable(this)) -> {
                AndroidUtility.showToast(this, getString(R.string.please_check_internet))
                return
            }
            fname == "" -> {
                AndroidUtility.showToast(this, "Firstname can't be blank")
                return
            }
            lname == "" -> {
                AndroidUtility.showToast(this, "Lastname can't be blank")
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

            password == "" -> {
                AndroidUtility.showToast(this, "Password can't be blank")
                return
            }
        }

        val signupRequest = SignupRequest().apply {
            this.source="MOB"
            this.first_name=fname
            this.last_name=lname
            this.email = email
            this.password = password
        }

        viewModel.requestsignup(this, signupRequest)




    }


}