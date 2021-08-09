package com.app.letschat.api
import com.app.letschat.model.BaseWrapperResponse
import com.app.letschat.model.ForgotpasswordRequest
import com.app.letschat.model.SigninRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun signin(@Body msigninRequest:SigninRequest):BaseWrapperResponse
    @POST("resetPasswordOtpSend")
    suspend fun forgotpassword(@Body mForgotpasswordRequest:ForgotpasswordRequest):BaseWrapperResponse


}