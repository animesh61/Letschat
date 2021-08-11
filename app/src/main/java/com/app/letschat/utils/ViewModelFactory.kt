package com.app.letschat.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.letschat.api.ApiHelper
import com.app.letschat.ui.forgotpassword.Changepasswordviewmodel
import com.app.letschat.ui.forgotpassword.ForgotpasswordViewmodel
import com.app.letschat.ui.login.Loginviewmodel
import com.app.letschat.ui.profile.Profileviewmodel
import com.app.letschat.ui.register.RegisterViewmodel
import com.app.letschat.ui.register.Verifyotpmodel

class ViewModelFactory(private val apiHelper: ApiHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Loginviewmodel::class.java)) {
            return Loginviewmodel(apiHelper) as T
        }
        if (modelClass.isAssignableFrom(ForgotpasswordViewmodel::class.java)) {
            return ForgotpasswordViewmodel(apiHelper) as T
        }
        if (modelClass.isAssignableFrom(Changepasswordviewmodel::class.java)) {
            return Changepasswordviewmodel(apiHelper) as T
        }
        if (modelClass.isAssignableFrom(RegisterViewmodel::class.java)) {
            return RegisterViewmodel(apiHelper) as T
        }

        if (modelClass.isAssignableFrom(Verifyotpmodel::class.java)) {
            return Verifyotpmodel(apiHelper) as T
        }
        if (modelClass.isAssignableFrom(Profileviewmodel::class.java)) {
            return Profileviewmodel(apiHelper) as T
        }


        /*

        if (modelClass.isAssignableFrom(ParallelNetworkCallsViewModel::class.java)) {
            return ParallelNetworkCallsViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(RoomDBViewModel::class.java)) {
            return RoomDBViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(TimeoutViewModel::class.java)) {
            return TimeoutViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(TryCatchViewModel::class.java)) {
            return TryCatchViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(ExceptionHandlerViewModel::class.java)) {
            return ExceptionHandlerViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(LongRunningTaskViewModel::class.java)) {
            return LongRunningTaskViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(TwoLongRunningTasksViewModel::class.java)) {
            return TwoLongRunningTasksViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(IgnoreErrorAndContinueViewModel::class.java)) {
            return IgnoreErrorAndContinueViewModel(apiHelper, dbHelper) as T
        }*/
        throw IllegalArgumentException("Unknown class name")
    }

}