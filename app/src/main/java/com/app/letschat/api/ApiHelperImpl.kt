package com.app.letschat.api

import com.app.letschat.model.BaseWrapperResponse
import com.app.letschat.model.ForgotpasswordRequest
import com.app.letschat.model.SigninRequest

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun signin(msigninRequest: SigninRequest): BaseWrapperResponse =
        apiService.signin(msigninRequest)

    override suspend fun forgotpassword(mforgotpassword: ForgotpasswordRequest): BaseWrapperResponse =
    apiService.forgotpassword(mforgotpassword)


}