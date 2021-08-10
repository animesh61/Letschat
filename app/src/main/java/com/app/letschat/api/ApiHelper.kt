package com.app.letschat.api


import com.app.letschat.model.*


interface ApiHelper {
    suspend fun signin(msigninRequest: SigninRequest): LoginResult
    suspend fun forgotpassword(mforgotpassword:ForgotpasswordRequest):BaseWrapperResponse
    suspend fun changepassword(mchangepassword:ChangepasswordRequest):ResetResult
    suspend fun register(msignuprequest:SignupRequest):RegisterResult


}