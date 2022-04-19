package com.example.shop_book

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_homeactivity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (application: Application): AndroidViewModel(application){
    val repo:Repository
    val allData: LiveData<List<Entity>>
    init {
        val dao= WordRoomDatabase.getDatabase(application).wordDao()
        repo=Repository(dao)
        allData=repo.data

    }
    fun insertion(Data:Entity)=viewModelScope.launch(Dispatchers.IO) {
        repo.insert(Data)
    }
    fun deletion(Data: Entity)=viewModelScope.launch(Dispatchers.IO) {
        repo.delete(Data)
    }




}