package com.app.letschat.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.letschat.api.ApiHelper
import com.app.letschat.model.ChangepasswordResult
import com.app.letschat.model.EditProfileResult
import com.app.letschat.model.changeprofilepasswordrequest
import com.app.letschat.model.editprofileRequest
import com.example.akaya.utils.Resource
import kotlinx.coroutines.launch

class ChangeprofilepasswordViewmodel(private val apiHelper: ApiHelper): ViewModel() {
    private var changeprofilepasswordlivedata= MutableLiveData<Resource<ChangepasswordResult>>()

    fun requestchangeprofilepassword(mContext: Context, changeprofilepasswordrequest: changeprofilepasswordrequest){
        viewModelScope.launch {
            changeprofilepasswordlivedata.postValue(Resource.loading(null))
            try {
                val changeprofilepasswordrequest = apiHelper.changeprofilepassword(changeprofilepasswordrequest)
                changeprofilepasswordlivedata.postValue(Resource.success(changeprofilepasswordrequest))


            }catch (ex:Exception){
                changeprofilepasswordlivedata.postValue(Resource.error(ex.toString(),null))
            }
        }
    }

    fun changepasswordprofilelivedata(): LiveData<Resource<ChangepasswordResult>> {
        return changeprofilepasswordlivedata
    }

}