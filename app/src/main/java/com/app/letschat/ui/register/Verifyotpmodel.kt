package com.app.letschat.ui.register

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.letschat.api.ApiHelper
import com.app.letschat.model.*
import com.example.akaya.utils.Resource
import kotlinx.coroutines.launch

class Verifyotpmodel (private val apiHelper: ApiHelper): ViewModel() {
    private var verifymodelLivedata= MutableLiveData<Resource<VerifyregResult>>()

    fun requestverifyotp(mContext: Context, verifyregisterRequest: verifyregisterRequest){
        viewModelScope.launch {
            verifymodelLivedata.postValue(Resource.loading(null))
            try {
                val verifyresponse = apiHelper.verifyregister(verifyregisterRequest)
                verifymodelLivedata.postValue(Resource.success(verifyresponse))


            }catch (ex:Exception){
                verifymodelLivedata.postValue(Resource.error(ex.toString(),null))
            }
        }
    }

    fun verifyotplivedata(): LiveData<Resource<VerifyregResult>> {
        return verifymodelLivedata
    }
}