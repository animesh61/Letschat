package com.app.letschat.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.letschat.api.ApiHelper
import com.app.letschat.model.EditProfileResult
import com.app.letschat.model.ProfileResult
import com.app.letschat.model.editprofileRequest
import com.app.letschat.model.profileRequest
import com.example.akaya.utils.Resource
import kotlinx.coroutines.launch

class Editprofileviewmodel(private val apiHelper: ApiHelper): ViewModel() {
    private var editprofileLivedata= MutableLiveData<Resource<EditProfileResult>>()

    fun requesteditprofile(mContext: Context, editprofileRequest: editprofileRequest){
        viewModelScope.launch {
            editprofileLivedata.postValue(Resource.loading(null))
            try {
                val editprofileresponse = apiHelper.editprofile(editprofileRequest)
                editprofileLivedata.postValue(Resource.success(editprofileresponse))


            }catch (ex:Exception){
                editprofileLivedata.postValue(Resource.error(ex.toString(),null))
            }
        }
    }

    fun editprofilelivadata(): LiveData<Resource<EditProfileResult>> {
        return editprofileLivedata
    }
}