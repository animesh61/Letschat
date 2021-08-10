package com.app.letschat.api
import com.app.letschat.model.*
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun signin(@Body msigninRequest:SigninRequest):LoginResult
    @POST("resetPasswordOtpSend")
    suspend fun forgotpassword(@Body mForgotpasswordRequest:ForgotpasswordRequest):BaseWrapperResponse
    @POST("resetPassword")
    suspend fun changepassword(@Body mchangepasswordrequest:ChangepasswordRequest):ResetResult
    @POST("register")
    suspend fun register(@Body msignuprequest:SignupRequest):RegisterResult


}