package com.app.letschat.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.letschat.api.ApiHelper
import com.app.letschat.model.LoginResult
import com.app.letschat.model.ProfileResult
import com.app.letschat.model.SigninRequest
import com.app.letschat.model.profileRequest
import com.example.akaya.utils.Resource
import kotlinx.coroutines.launch

class Profileviewmodel (private val apiHelper: ApiHelper): ViewModel() {
    private var profileLivedata= MutableLiveData<Resource<ProfileResult>>()

    fun requestprofile(mContext: Context, profileRequest: profileRequest){
        viewModelScope.launch {
            profileLivedata.postValue(Resource.loading(null))
            try {
                val profileresponse = apiHelper.profile(profileRequest)
                profileLivedata.postValue(Resource.success(profileresponse))


            }catch (ex:Exception){
                profileLivedata.postValue(Resource.error(ex.toString(),null))
            }
        }
    }

    fun profilelivedata(): LiveData<Resource<ProfileResult>> {
        return profileLivedata
    }
}