package com.app.letschat.ui.profile

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.letschat.R
import com.app.letschat.api.ApiHelperImpl
import com.app.letschat.api.RetrofitBuilder
import com.app.letschat.dialog.CustomLoaderDialog
import com.app.letschat.model.changeprofilepasswordrequest
import com.app.letschat.ui.login.LoginActivity
import com.app.letschat.utils.ViewModelFactory
import com.example.akaya.utils.AndroidUtility
import com.example.akaya.utils.Prefs
import com.example.akaya.utils.Status

class Changepassword:AppCompatActivity() {
    lateinit var et_old_password:EditText
    lateinit var et_new_passwd:EditText
    lateinit var btn_submit:Button
    lateinit var iv_password: ImageView
    lateinit var iv_password1: ImageView

    private lateinit var viewModel: ChangeprofilepasswordViewmodel
    lateinit var mCustomLoaderDialog: CustomLoaderDialog
    var user_id1:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_change_password)
        et_old_password=findViewById(R.id.et_old_password)
        et_new_passwd=findViewById(R.id.et_new_passwd)
        btn_submit=findViewById(R.id.btn_submit)
        user_id1 = Prefs.with(this).read("user_id")
        iv_password=findViewById(R.id.iv_password)
        iv_password1=findViewById(R.id.iv_password1)
        iv_password.setOnClickListener{
            if(et_old_password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((iv_password)).setImageResource(R.drawable.ic_password)

                //Show Password
                et_old_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((iv_password)).setImageResource(R.drawable.ic_password);

                //Hide Password
                et_old_password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }

        }

        iv_password1.setOnClickListener{
            if(et_new_passwd.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((iv_password1)).setImageResource(R.drawable.ic_password)

                //Show Password
                et_new_passwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((iv_password1)).setImageResource(R.drawable.ic_password);

                //Hide Password
                et_new_passwd.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }

        }


        mCustomLoaderDialog = CustomLoaderDialog(this)

        setUpViewModel()

        setupObserver()
        btn_submit.setOnClickListener{
            changeprofilePasswordApi()
        }

    }
    fun showLoader() {
        mCustomLoaderDialog.show()
    }

    fun hideLoader() {
        if (mCustomLoaderDialog.isShowing)
            mCustomLoaderDialog.cancel()
    }

    private fun changeprofilePasswordApi(){
        val old_password=et_old_password.text.toString().trim()
        val newpasswd=et_new_passwd.text.toString().trim()

        when {
            (!AndroidUtility.isNetworkAvailable(this)) -> {
                AndroidUtility.showToast(this, getString(R.string.please_check_internet))
                return
            }
            old_password == "" -> {
                AndroidUtility.showToast(this, "Old Password can't be blank")
                return

            }
            newpasswd ==""->{
                AndroidUtility.showToast(this, "New Password can't be blank")
                return
            }

        }

        val changePasswordRequest = changeprofilepasswordrequest().apply {

            this.source = "MOB"
            this.user_id=user_id1
            this.old_password=old_password
            this.password=newpasswd
        }

        viewModel.requestchangeprofilepassword(this,changePasswordRequest)


    }

    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        ).get(ChangeprofilepasswordViewmodel::class.java)
    }
    private fun setupObserver() {
        viewModel.changepasswordprofilelivedata().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    hideLoader()
                    val baseResponse = it.data
                    val errorCode = baseResponse?.status?.error_code7

                    when {
                        (errorCode == 0) -> {
                            AndroidUtility.showToast(this, "Password Changed Successfully!!!")
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