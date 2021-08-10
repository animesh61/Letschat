package com.app.letschat.ui.forgotpassword

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.letschat.api.ApiHelper
import com.app.letschat.model.BaseWrapperResponse
import com.app.letschat.model.ChangepasswordRequest
import com.app.letschat.model.ForgotpasswordRequest
import com.app.letschat.model.ResetResult
import com.example.akaya.utils.Resource
import kotlinx.coroutines.launch

class Changepasswordviewmodel(private val apiHelper: ApiHelper): ViewModel() {
    private var changepasswordlivedata= MutableLiveData<Resource<ResetResult>>()

    fun requestchangepassword(mContext: Context, changepasswordRequest: ChangepasswordRequest){
        viewModelScope.launch {
            changepasswordlivedata.postValue(Resource.loading(null))
            try {
                val changepasswordresponse = apiHelper.changepassword(changepasswordRequest)
                changepasswordlivedata.postValue(Resource.success(changepasswordresponse))


            }catch (ex:Exception){
                changepasswordlivedata.postValue(Resource.error(ex.toString(),null))
            }
        }
    }

    fun changepasswordLivedata(): LiveData<Resource<ResetResult>> {
        return changepasswordlivedata
    }


}