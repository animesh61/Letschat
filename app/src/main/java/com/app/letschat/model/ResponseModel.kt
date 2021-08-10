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
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("first_name")
    var first_name: String? = null,
    @SerializedName("last_name")
    var last_name: String? = null)

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
