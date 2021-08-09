package com.app.letschat.api

import com.app.letschat.model.BaseWrapperResponse
import com.app.letschat.model.ForgotpasswordRequest
import com.app.letschat.model.SigninRequest


interface ApiHelper {
    suspend fun signin(msigninRequest: SigninRequest): BaseWrapperResponse
    suspend fun forgotpassword(mforgotpassword:ForgotpasswordRequest):BaseWrapperResponse


}