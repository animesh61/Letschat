package com.app.letschat.ui.profile

import android.annotation.TargetApi
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.letschat.R
import com.app.letschat.api.ApiHelperImpl
import com.app.letschat.api.RetrofitBuilder
import com.app.letschat.dialog.CustomLoaderDialog
import com.app.letschat.model.editprofileRequest
import com.app.letschat.utils.ViewModelFactory
import com.example.akaya.utils.AndroidUtility
import com.example.akaya.utils.Prefs
import com.example.akaya.utils.Status
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File

class EditProfile : AppCompatActivity() {
    lateinit var et_editfname: EditText
    lateinit var et_editlname:EditText
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
    var firstname: String? = null
    var lastname: String? = null
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
    lateinit var fl_img: FrameLayout
    private val OPERATION_CAPTURE_PHOTO = 1
    private val OPERATION_CHOOSE_PHOTO = 2
    var picturePath: String? = null
    lateinit var profile_image: CircleImageView
    private var mUri: Uri? = null
    var imagePath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        et_editfname = findViewById(R.id.et_editfname)
        et_editlname=findViewById(R.id.et_editlname)
        et_edit_department = findViewById(R.id.et_edit_department)
        et_edit_job_title = findViewById(R.id.et_edit_job_title)
        et_edit_company = findViewById(R.id.et_edit_company)
        et_edit_location = findViewById(R.id.et_edit_location)
        et_edit_phone = findViewById(R.id.et_edit_phone)
        et_edit_language = findViewById(R.id.et_edit_language)
        et_edit_timezone = findViewById(R.id.et_edit_timezone)
        btn_update = findViewById(R.id.btn_update)
        fl_img = findViewById(R.id.fl_img)
        profile_image = findViewById(R.id.profile_image)
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




        if (et_editfname.text.toString().trim().equals(" ")) {
            et_editfname.setText(" ")
        } else {
            et_editfname.setText(fname)

       }
        if (et_editlname.text.toString().trim().equals(" ")) {
            et_editlname.setText(" ")
        } else {
            et_editlname.setText(lname)

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

        fl_img.setOnClickListener {
            selectimage()
        }

        //  editprofileapi()


        mCustomLoaderDialog = CustomLoaderDialog(this)

        setUpViewModel()

        setupObserver()
        btn_update.setOnClickListener({
            editprofileapi()
        })


    }

    private fun selectimage() {
        val options = arrayOf<CharSequence>("From Camera", "From Gallery", "Cancel")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add Photo!")
        builder.setItems(options) { dialog, item ->
            if (options[item] == "From Camera") {

              //  capturePhoto()

                val checkSelfPermission = ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CAMERA
                )
                if (checkSelfPermission != PackageManager.PERMISSION_GRANTED){
                    //Requests permissions to be granted to this application at runtime
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.CAMERA), 2
                    )
                }
                else{
                    capturePhoto()
                }

            } else if (options[item] == "From Gallery") {

                val checkSelfPermission = ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                if (checkSelfPermission != PackageManager.PERMISSION_GRANTED){
                    //Requests permissions to be granted to this application at runtime
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1
                    )
                }
                else{
                    openGallery()
                }

            } else if (options[item] == "Cancel") {
                dialog.dismiss()
            }
        }
        builder.show()

    }
    private fun capturePhoto(){
        val capturedImage = File(externalCacheDir, "My_Captured_Photo.jpg")
        if(capturedImage.exists()) {
            capturedImage.delete()
        }
        capturedImage.createNewFile()
        mUri = if(Build.VERSION.SDK_INT >= 24){
            FileProvider.getUriForFile(
                this, "com.app.letschat.fileprovider",
                capturedImage
            )
        } else {
            Uri.fromFile(capturedImage)
        }

        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri)
        startActivityForResult(intent, OPERATION_CAPTURE_PHOTO)
    }
    private fun openGallery(){
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"
        startActivityForResult(intent, OPERATION_CHOOSE_PHOTO)
    }



    private fun renderImage(imagePath: String?){
        if (imagePath != null) {
            val bitmap = BitmapFactory.decodeFile(imagePath)
            profile_image?.setImageBitmap(bitmap)
        }
        else {
            AndroidUtility.showToast(this, "ImagePath is null")
        }
    }
    private fun getImagePath(uri: Uri?, selection: String?): String {
        var path: String? = null
        val cursor = contentResolver.query(uri!!, null, selection, null, null)
        if (cursor != null){
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            }
            cursor.close()
        }
        return path!!
    }
    @TargetApi(19)
    private fun handleImageOnKitkat(data: Intent?) {
       // var imagePath: String? = null
        val uri = data!!.data
        //DocumentsContract defines the contract between a documents provider and the platform.
        if (DocumentsContract.isDocumentUri(this, uri)){
            val docId = DocumentsContract.getDocumentId(uri)
            if ("com.android.providers.media.documents" == uri!!.authority){
                val id = docId.split(":")[1]
                val selsetion = MediaStore.Images.Media._ID + "=" + id
                imagePath = getImagePath(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    selsetion
                )
            }
            else if ("com.android.providers.downloads.documents" == uri.authority){
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse(
                        "content://downloads/public_downloads"
                    ), java.lang.Long.valueOf(docId)
                )
                imagePath = getImagePath(contentUri, null)
            }
        }
        else if ("content".equals(uri!!.scheme, ignoreCase = true)){
            imagePath = getImagePath(uri, null)
        }
        else if ("file".equals(uri.scheme, ignoreCase = true)){
            imagePath = uri.path
        }
        renderImage(imagePath)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantedResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantedResults)
        when(requestCode){
            1 ->
                if (grantedResults.isNotEmpty() && grantedResults.get(0) ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    openGallery()
                } else {
                    AndroidUtility.showToast(
                        this,
                        "Unfortunately You are Denied Permission to Perform this Operataion."
                    )
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            OPERATION_CAPTURE_PHOTO ->
                if (resultCode == Activity.RESULT_OK) {
                    val bitmap = BitmapFactory.decodeStream(
                        getContentResolver().openInputStream(mUri!!)
                    )
                  //  editprofileapi()
                    profile_image!!.setImageBitmap(bitmap)
                }
            OPERATION_CHOOSE_PHOTO ->
                if (resultCode == Activity.RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitkat(data)
                     //   editprofileapi()
                    }
                }
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
                           // et_editfname.setText(firstname)
                          //  et_editlname.setText(lastname)
//                            et_edit_department.setText(department_edit)
//                            et_edit_job_title.setText(job_title_edit)
//                            et_edit_company.setText(company_edit)````
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
        val fname = et_editfname.text.toString().trim()
        val lname = et_editlname.text.toString().trim()
        val department = et_edit_department.text.toString().trim()
        val job_title = et_edit_job_title.text.toString().trim()
        val company = et_edit_company.text.toString().trim()
        val location = et_edit_location.text.toString().trim()
        val phone = et_edit_phone.text.toString().trim()
        val language = et_edit_language.text.toString().trim()
        val timezone = et_edit_timezone.text.toString().trim()
       // val file = File(imagePath)




        when {
            (!AndroidUtility.isNetworkAvailable(this)) -> {
                AndroidUtility.showToast(this, getString(R.string.please_check_internet))
                return
            }
            fname == "" -> {
                AndroidUtility.showToast(this, "First Name can't be blank")
                return
            }
            lname == "" -> {
                AndroidUtility.showToast(this, "Last Name can't be blank")
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
            this.first_name = et_editfname.text.toString().trim()
            this.last_name=et_editlname.text.toString().trim()
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
          //  this.image_upload=file
           // Log.e("image_upload", image_upload.toString())
        }

        viewModel.requesteditprofile(this, editprofileRequest)


    }


}