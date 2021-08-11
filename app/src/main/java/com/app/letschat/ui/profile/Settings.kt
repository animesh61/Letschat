package com.app.letschat.ui.profile

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.letschat.R
import com.app.letschat.api.ApiHelperImpl
import com.app.letschat.api.RetrofitBuilder
import com.app.letschat.dialog.CustomLoaderDialog
import com.app.letschat.model.SigninRequest
import com.app.letschat.model.profileRequest
import com.app.letschat.ui.Contact
import com.app.letschat.ui.forgotpassword.HomeActivity
import com.app.letschat.ui.login.Loginviewmodel
import com.app.letschat.ui.startmetting.Meeting
import com.app.letschat.utils.ViewModelFactory
import com.bumptech.glide.Glide
import com.example.akaya.utils.AndroidUtility
import com.example.akaya.utils.Prefs
import com.example.akaya.utils.Status

class Settings:AppCompatActivity() {
    lateinit var ll_home:LinearLayout
    lateinit var ll_meeting:LinearLayout
    lateinit var ll_contacts:LinearLayout
    lateinit var ll_seetings:LinearLayout
    lateinit var tv_name:TextView
    lateinit var tv_email:TextView
    lateinit var iv_profile:ImageView
    lateinit var iv_edit:ImageView
    private lateinit var viewModel: Profileviewmodel
    lateinit var mCustomLoaderDialog: CustomLoaderDialog
    var user_id1:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seetings)
        ll_home=findViewById(R.id.ll_home)
        ll_meeting=findViewById(R.id.ll_meeting)
        ll_contacts=findViewById(R.id.ll_contacts)
        ll_seetings=findViewById(R.id.ll_seetings)
        tv_name=findViewById(R.id.tv_name)
        tv_email=findViewById(R.id.tv_email)
        iv_profile=findViewById(R.id.iv_profile)
        iv_edit=findViewById(R.id.iv_edit)
        user_id1 = Prefs.with(this).read("user_id")

        mCustomLoaderDialog = CustomLoaderDialog(this)


        setUpViewModel()

        setupObserver()
        profileapi()

        ll_home.setOnClickListener({
            val i= Intent(this, HomeActivity::class.java)
            startActivity(i)
        })
        ll_meeting.setOnClickListener({
            val i= Intent(this, Meeting::class.java)
            startActivity(i)
        })
        ll_contacts.setOnClickListener({
            val i= Intent(this,Contact::class.java)
            startActivity(i)
        })
        ll_seetings.setOnClickListener({
            val i= Intent(this,Settings::class.java)
            startActivity(i)
        })
        iv_edit.setOnClickListener({
            val i= Intent(this,EditProfile::class.java)
            startActivity(i)

        })


    }
    private fun profileapi(){
        val profileRequest = profileRequest().apply {

            this.source="MOB"
            this.user_id=user_id1
        }

        viewModel.requestprofile(this, profileRequest)


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
        ).get(Profileviewmodel::class.java)
    }
    private fun setupObserver() {
        viewModel.profilelivedata().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    hideLoader()
                    val baseResponse = it.data
                    val errorCode = baseResponse?.status?.error_code5
                    val fname=baseResponse?.result?.data?.first_name
                    val lname=baseResponse?.result?.data?.last_name
                    val email=baseResponse?.result?.data?.email
                    val image=baseResponse?.result?.data?.profile_image
                    when {
                        (errorCode == 0) -> {
                            AndroidUtility.showToast(this, baseResponse?.status?.message?:"")
                            tv_name.setText(fname + " "+ lname)
                            tv_email.setText(email)
                            Glide.with(this)
                                .load(image)
                                .placeholder(R.mipmap.ic_launcher)
                                .error(R.mipmap.ic_launcher)
                                .into(iv_profile)




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