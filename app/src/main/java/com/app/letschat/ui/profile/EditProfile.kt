package com.app.letschat.ui.profile

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Settings
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.letschat.R
import com.app.letschat.api.ApiHelperImpl
import com.app.letschat.api.RetrofitBuilder
import com.app.letschat.dialog.CustomLoaderDialog
import com.app.letschat.model.SigninRequest
import com.app.letschat.model.editprofileRequest
import com.app.letschat.ui.forgotpassword.HomeActivity
import com.app.letschat.ui.login.Loginviewmodel
import com.app.letschat.utils.ViewModelFactory
import com.example.akaya.utils.AndroidUtility
import com.example.akaya.utils.Prefs
import com.example.akaya.utils.Status

class EditProfile : AppCompatActivity() {
    lateinit var et_editname: EditText
    lateinit var et_edit_department: EditText
    lateinit var et_edit_job_title: EditText
    lateinit var et_edit_company: EditText
    lateinit var et_edit_location: EditText
    lateinit var et_edit_phone: EditText
    lateinit var et_edit_language: EditText
    lateinit var et_edit_timezone: EditText
    lateinit var btn_update: Button
    private lateinit var viewModel: Editprofileviewmodel
    lateinit var mCustomLoaderDialog: CustomLoaderDialog
    var user_id1: String? = null
    var firstname:String?=null
    var lastname:String?=null
    var fname: String? = null
    var lname: String? = null
    var email1: String? = null
    var department: String? = null
    var job_title: String? = null
    var company: String? = null
    var location: String? = null
    var phone: String? = null
    var language: String? = null
    var timeZone: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        et_editname = findViewById(R.id.et_editname)
        et_edit_department = findViewById(R.id.et_edit_department)
        et_edit_job_title = findViewById(R.id.et_edit_job_title)
        et_edit_company = findViewById(R.id.et_edit_company)
        et_edit_location = findViewById(R.id.et_edit_location)
        et_edit_phone = findViewById(R.id.et_edit_phone)
        et_edit_language = findViewById(R.id.et_edit_language)
        et_edit_timezone = findViewById(R.id.et_edit_timezone)
        btn_update = findViewById(R.id.btn_update)
        user_id1 = Prefs.with(this).read("user_id")
        fname = intent.getStringExtra("fname")
        lname = intent.getStringExtra("lname")
        email1 = intent.getStringExtra("email")
        department = intent.getStringExtra("department")
        job_title = intent.getStringExtra("job_title")
        company = intent.getStringExtra("company")
        location = intent.getStringExtra("location")
        phone = intent.getStringExtra("phone")
        language = intent.getStringExtra("language")
        timeZone = intent.getStringExtra("time_zone")


        //  et_editname.setText(fname + " " + lname)

        if (et_editname.text.toString().trim().equals(" ")) {
            et_editname.setText(" ")
        } else {
            et_editname.setText(fname + " " + lname)

        }
        if (et_edit_department.text.toString().trim().equals(" ")) {
            et_edit_department.setText(" ")
        } else {
            et_edit_department.setText(department)

        }
        if (et_edit_job_title.text.toString().trim().equals(" ")) {
            et_edit_job_title.setText(" ")

        } else {
            et_edit_job_title.setText(job_title)

        }
        if (et_edit_company.text.toString().trim().equals(" ")) {
            et_edit_company.setText(" ")

        } else {
            et_edit_company.setText(company)

        }
        if (et_edit_location.text.toString().trim().equals(" ")) {
            et_edit_location.setText(" ")

        } else {
            et_edit_location.setText(location)

        }
        if (et_edit_phone.text.toString().trim().equals(" ")) {
            et_edit_phone.setText(" ")

        } else {
            et_edit_phone.setText(phone)

        }
        if (et_edit_language.text.toString().trim().equals(" ")) {
            et_edit_language.setText(" ")

        } else {
            et_edit_language.setText(language)

        }
        if (et_edit_timezone.text.toString().trim().equals(" ")) {
            et_edit_timezone.setText(" ")

        } else {
            et_edit_timezone.setText(timeZone)

        }

        //  editprofileapi()


        mCustomLoaderDialog = CustomLoaderDialog(this)

        setUpViewModel()

        setupObserver()
        btn_update.setOnClickListener({
            editprofileapi()
        })

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
        ).get(Editprofileviewmodel::class.java)
    }

    private fun setupObserver() {
        viewModel.editprofilelivadata().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    hideLoader()
                    val baseResponse = it.data
                    val errorCode = baseResponse?.status?.error_code6
                     firstname = baseResponse?.result?.data?.first_name
                     lastname = baseResponse?.result?.data?.last_name
                    val department_edit = baseResponse?.result?.data?.department
                    val job_title_edit = baseResponse?.result?.data?.job_title
                    val company_edit = baseResponse?.result?.data?.company
                    val location_edit = baseResponse?.result?.data?.address
                    val phone_edit = baseResponse?.result?.data?.phone
                    val language_edit = baseResponse?.result?.data?.language
                    val timeZone_edit = baseResponse?.result?.data?.time_zone

                    when {
                        (errorCode == 0) -> {
                            AndroidUtility.showToast(this, baseResponse?.status?.message ?: "")
//                            et_editname.setText(fname + " " + lname)
//                            et_edit_department.setText(department_edit)
//                            et_edit_job_title.setText(job_title_edit)
//                            et_edit_company.setText(company_edit)
//                            et_edit_location.setText(location_edit)
//                            et_edit_phone.setText(phone_edit)
//                            et_edit_language.setText(language_edit)
//                            et_edit_timezone.setText(timeZone_edit)
//
//
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

    private fun editprofileapi() {
        val name = et_editname.text.toString().trim()
        val department = et_edit_department.text.toString().trim()
        val job_title = et_edit_job_title.text.toString().trim()
        val company = et_edit_company.text.toString().trim()
        val location = et_edit_location.text.toString().trim()
        val phone = et_edit_phone.text.toString().trim()
        val language = et_edit_language.text.toString().trim()
        val timezone = et_edit_timezone.text.toString().trim()



        when {
            (!AndroidUtility.isNetworkAvailable(this)) -> {
                AndroidUtility.showToast(this, getString(R.string.please_check_internet))
                return
            }
            name == "" -> {
                AndroidUtility.showToast(this, "Name can't be blank")
                return
            }

            department == "" -> {
                AndroidUtility.showToast(this, "Department can't be blank")
                return
            }

            job_title == "" -> {
                AndroidUtility.showToast(this, "Job Title can't be blank")
                return
            }
            company == "" -> {
                AndroidUtility.showToast(this, "Company can't be blank")
                return
            }
            location == "" -> {
                AndroidUtility.showToast(this, "Location can't be blank")
                return
            }
            phone == "" -> {
                AndroidUtility.showToast(this, "Phone can't be blank")
                return
            }
            language == "" -> {
                AndroidUtility.showToast(this, "Language can't be blank")
                return
            }
            timezone == "" -> {
                AndroidUtility.showToast(this, "Time zone can't be blank")
                return
            }


        }

        val editprofileRequest = editprofileRequest().apply {
            this.source = "MOB"
            this.user_id = user_id1
            this.first_name = fname
            this.last_name = lname
            this.phone = et_edit_phone.text.toString().trim()
            this.email = email1
            this.company = et_edit_company.text.toString().trim()
            this.language = et_edit_language.text.toString().trim()
            this.department = et_edit_department.text.toString().trim()
            this.job_title = et_edit_job_title.text.toString().trim()
            this.address = et_edit_location.text.toString().trim()
            this.time_zone = et_edit_timezone.text.toString().trim()
            this.personalMeetingID = ""
            this.instantMeeting = ""
            this.hostKey = ""
        }

        viewModel.requesteditprofile(this, editprofileRequest)


    }


}