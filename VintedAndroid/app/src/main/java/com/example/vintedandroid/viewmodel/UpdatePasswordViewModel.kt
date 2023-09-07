package com.example.vintedandroid.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.swagger.client.apis.UserApi

class UpdatePasswordViewModel() : ViewModel() {

    fun updatePassword(newPassword: String, newPassword2: String): Boolean {

        if (newPassword.equals(newPassword2)) {
            UserApi().updateUserPassword(newPassword.replace("\"", ""))
            return true
        }
        return false
    }
}