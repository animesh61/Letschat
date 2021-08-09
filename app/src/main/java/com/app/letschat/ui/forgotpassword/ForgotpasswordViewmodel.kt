package com.app.letschat.ui.forgotpassword

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.letschat.api.ApiHelper
import com.app.letschat.model.BaseWrapperResponse
import com.app.letschat.model.ForgotpasswordRequest
import com.app.letschat.model.SigninRequest
import com.example.akaya.utils.Resource
import kotlinx.coroutines.launch

class ForgotpasswordViewmodel(private val apiHelper: ApiHelper): ViewModel() {
    private var forgotpasswordlivedata= MutableLiveData<Resource<BaseWrapperResponse>>()

    fun requestforgotpassword(mContext: Context, forgotpasswordRequest: ForgotpasswordRequest){
        viewModelScope.launch {
            forgotpasswordlivedata.postValue(Resource.loading(null))
            try {
                val forgotpasswordresponse = apiHelper.forgotpassword(forgotpasswordRequest)
                forgotpasswordlivedata.postValue(Resource.success(forgotpasswordresponse))


            }catch (ex:Exception){
                forgotpasswordlivedata.postValue(Resource.error(ex.toString(),null))
            }
        }
    }

    fun forgotpasswordlivedata(): LiveData<Resource<BaseWrapperResponse>> {
        return forgotpasswordlivedata
    }



}