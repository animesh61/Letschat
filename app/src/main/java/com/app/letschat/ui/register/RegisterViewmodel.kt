package com.app.letschat.ui.register

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.letschat.api.ApiHelper
import com.app.letschat.model.BaseWrapperResponse
import com.app.letschat.model.ForgotpasswordRequest
import com.app.letschat.model.RegisterResult
import com.app.letschat.model.SignupRequest
import com.example.akaya.utils.Resource
import kotlinx.coroutines.launch

class RegisterViewmodel(private val apiHelper: ApiHelper): ViewModel() {
    private var signupLivedata= MutableLiveData<Resource<RegisterResult>>()

    fun requestsignup(mContext: Context, signupRequest: SignupRequest){
        viewModelScope.launch {
            signupLivedata.postValue(Resource.loading(null))
            try {
                val signupresponse = apiHelper.register(signupRequest)
                signupLivedata.postValue(Resource.success(signupresponse))


            }catch (ex:Exception){
                signupLivedata.postValue(Resource.error(ex.toString(),null))
            }
        }
    }

    fun signuplivedata(): LiveData<Resource<RegisterResult>> {
        return signupLivedata
    }
}