package com.app.letschat.model

import com.google.gson.annotations.SerializedName

data class ResetResult(
    @SerializedName("status")
    var status: Status1? = null,
    @SerializedName("result")
    var result: Result1? = null
)

data class Status1(
    @SerializedName("error_code")
    var error_code1: Int=0,
    @SerializedName("message")
      var message: String?=null
)
data class Result1(
    @SerializedName("userdetails")
    var userdetails: Userdetails? = null)
data class Userdetails(
    @SerializedName("user_id")
    var user_id: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("first_name")
    var first_name: String? = null,
    @SerializedName("last_name")
    var last_name: String? = null)

data class LoginResult(
    @SerializedName("status")
    var status: Status2? = null,
    @SerializedName("result")
    var result: Result2? = null
)

data class Status2(
    @SerializedName("error_code")
    var error_code2: Int=0,
    @SerializedName("message")
    var message: String?=null
)
data class Result2(
    @SerializedName("data")
    var data: Data? = null)
data class Data(
    @SerializedName("user_id")
    var user_id: String? = null,
    @SerializedName("first_name")
    var first_name: String? = null,
    @SerializedName("last_name")
    var last_name: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("alt_email")
    var alt_email: String? = null,
    @SerializedName("phone")
    var phone: String? = null ,
    @SerializedName("alt_phone")
    var alt_phone: String? = null ,
    @SerializedName("company")
    var company: String? = null ,
   @SerializedName("profile_image")
      var profile_image:String?=null)


data class RegisterResult(
    @SerializedName("status")
    var status: Status3? = null,
    @SerializedName("result")
    var result: Result3? = null
)

data class Status3(
    @SerializedName("error_code")
    var error_code3: Int=0,
    @SerializedName("message")
    var message: String?=null
)
data class Result3(
    @SerializedName("data")
    var data: Int = 0)


data class VerifyregResult(
    @SerializedName("status")
    var status: Status4? = null,
    @SerializedName("result")
    var result: Result4? = null
)

data class Status4(
    @SerializedName("error_code")
    var error_code4: Int=0,
    @SerializedName("message")
    var message: String?=null
)
data class Result4(
    @SerializedName("userdetails")
    var userdetails: Userdetails?=null)

data class ProfileResult(
    @SerializedName("status")
    var status: Status5? = null,
    @SerializedName("result")
    var result: Result5? = null
)

data class Status5(
    @SerializedName("error_code")
    var error_code5: Int=0,
    @SerializedName("message")
    var message: String?=null
)
data class Result5(
    @SerializedName("data")
    var data: Data2? = null)
data class Data2(
    @SerializedName("user_id")
    var user_id: String? = null,
    @SerializedName("role_id")
    var role_id:String?=null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("first_name")
    var first_name: String? = null,
    @SerializedName("last_name")
    var last_name: String? = null,
    @SerializedName("phone")
    var phone: String? = null ,
    @SerializedName("company")
    var company:String?=null,
    @SerializedName("language")
    var language:String?=null,
    @SerializedName("department")
    var department:String?=null,
    @SerializedName("job_title")
    var job_title:String?=null,
    @SerializedName("address")
    var address:String?=null,
    @SerializedName("time_zone")
    var time_zone:String?=null,
    @SerializedName("personalMeetingID")
    var personalMeetingID:String?=null,
    @SerializedName("instantMeeting")
    var instantMeeting:String?=null,
    @SerializedName("hostKey")
    var hostKey:String?=null,
    @SerializedName("profile_image")
    var profile_image:String?=null)

data class EditProfileResult(
    @SerializedName("status")
    var status: Status6? = null,
    @SerializedName("result")
    var result: Result6? = null
)

data class Status6(
    @SerializedName("error_code")
    var error_code6: Int=0,
    @SerializedName("message")
    var message: String?=null
)
data class Result6(
    @SerializedName("data")
    var data: Data3? = null)
data class Data3(
    @SerializedName("user_id")
    var user_id: String? = null,
    @SerializedName("role_id")
    var role_id:String?=null,
    @SerializedName("first_name")
    var first_name: String? = null,
    @SerializedName("last_name")
    var last_name: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("phone")
    var phone: String? = null ,
    @SerializedName("company")
    var company:String?=null,
    @SerializedName("language")
    var language:String?=null,
    @SerializedName("department")
    var department:String?=null,
    @SerializedName("job_title")
    var job_title:String?=null,
    @SerializedName("address")
    var address:String?=null,
    @SerializedName("time_zone")
    var time_zone:String?=null,
    @SerializedName("personalMeetingID")
    var personalMeetingID:String?=null,
    @SerializedName("instantMeeting")
    var instantMeeting:String?=null,
    @SerializedName("hostKey")
    var hostKey:String?=null,
    @SerializedName("profile_image")
    var profile_image:String?=null)
data class ChangepasswordResult(
    @SerializedName("status")
    var status: Status7? = null,
    @SerializedName("result")
    var result: Result7? = null
)


data class Status7(
    @SerializedName("error_code")
    var error_code7: Int=0,
    @SerializedName("message")
    var message: String?=null
)
data class Result7(
    @SerializedName("data")
    var data:String?=null
)






