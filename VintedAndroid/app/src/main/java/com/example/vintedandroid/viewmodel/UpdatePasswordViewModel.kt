package com.example.vintedandroid.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.swagger.client.apis.UserApi

class UpdatePasswordViewModel(application: Application) : ViewModel() {

    private val application = application

    fun updatePassword(newPassword: String, newPassword2: String): Boolean{

        if(newPassword.equals(newPassword2)){
            //TODO
            //UserApi().updateUserPassword(newPassword)
            return true
        }
        return false
    }


            /*
            fun getAllInsertion(): PageBasicInsertionDto {
        var insertion = InsertionApi().all4(0)
        Log.i("HomeViewModel::class", "getAllInsertion -> $insertion")
        return insertion
    }
             */
}