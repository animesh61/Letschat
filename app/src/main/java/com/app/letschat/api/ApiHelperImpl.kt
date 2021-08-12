package com.app.letschat.api

import com.app.letschat.model.*

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun signin(msigninRequest: SigninRequest): LoginResult =
        apiService.signin(msigninRequest)

    override suspend fun forgotpassword(mforgotpassword: ForgotpasswordRequest): BaseWrapperResponse =
    apiService.forgotpassword(mforgotpassword)

    override suspend fun changepassword(mchangepassword: ChangepasswordRequest): ResetResult =
        apiService.changepassword(mchangepassword)

    override suspend fun register(msignuprequest: SignupRequest): RegisterResult =
     apiService.register(msignuprequest)

    override suspend fun verifyregister(mverifyregisterRequest: verifyregisterRequest): VerifyregResult =
    apiService.verifyregister(mverifyregisterRequest)

    override suspend fun profile(mprofileRequest: profileRequest): ProfileResult =
       apiService.profile(mprofileRequest)

    override suspend fun editprofile(meditprofilerequest: editprofileRequest):EditProfileResult=
    apiService.editprofile(meditprofilerequest)

    override suspend fun changeprofilepassword(mchangeprofilepasswordrequest: changeprofilepasswordrequest): ChangepasswordResult =
        apiService.changeprofilepassword(mchangeprofilepasswordrequest)



}