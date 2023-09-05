package com.example.vintedandroid.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.swagger.client.apis.InsertionApi
import com.example.vintedandroid.swagger.client.models.PageBasicInsertionDto

class MyInsertionViewModel(application: Application) : ViewModel() {

    fun getMyInsertion(page : Int): PageBasicInsertionDto{
        return InsertionApi().getMyInsertion(page)
    }

}