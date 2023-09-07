package com.example.vintedandroid.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.swagger.client.apis.InsertionApi
import com.example.vintedandroid.swagger.client.models.BasicInsertionDto
import com.example.vintedandroid.swagger.client.models.PageBasicInsertionDto

class MyInsertionViewModel() : ViewModel() {

    private val insertions : SnapshotStateList<BasicInsertionDto> = mutableStateListOf()

    fun getMyInsertion(page : Int): SnapshotStateList<BasicInsertionDto>{
        val pageInsertion =InsertionApi().getMyInsertion(page)
        insertions.addAll(pageInsertion.results)
        return insertions
    }

}