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
    @POST("activeEmail")
    suspend fun verifyregister(@Body mverifyregisterRequest:verifyregisterRequest):VerifyregResult
    @POST("profileDetails")
    suspend fun profile(@Body mprofilerequest:profileRequest):ProfileResult
    @POST("profileDetailsUpdate")
    suspend fun editprofile(@Body meditprofilerequest:editprofileRequest):EditProfileResult
    @POST("changePassword")
    suspend fun changeprofilepassword(@Body mchangeprofilepasswordrequest:changeprofilepasswordrequest):ChangepasswordResult


}