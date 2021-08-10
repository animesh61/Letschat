package com.app.letschat.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.letschat.api.ApiHelper
import com.app.letschat.model.BaseWrapperResponse
import com.app.letschat.model.LoginResult
import com.app.letschat.model.SigninRequest
import com.example.akaya.utils.Resource
import kotlinx.coroutines.launch

class Loginviewmodel(private val apiHelper: ApiHelper): ViewModel() {
    private var signinLivedata= MutableLiveData<Resource<LoginResult>>()

    fun requestsignin(mContext: Context, signinRequest: SigninRequest){
        viewModelScope.launch {
            signinLivedata.postValue(Resource.loading(null))
            try {
                val signinresponse = apiHelper.signin(signinRequest)
                signinLivedata.postValue(Resource.success(signinresponse))


            }catch (ex:Exception){
                signinLivedata.postValue(Resource.error(ex.toString(),null))
            }
        }
    }

    fun signinData(): LiveData<Resource<LoginResult>> {
        return signinLivedata
    }



}