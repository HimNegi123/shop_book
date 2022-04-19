package com.example.shop_book

import androidx.lifecycle.LiveData

class Repository(private val Data:dataDao){
    val data: LiveData<List<Entity>> =Data.getData()
    suspend fun insert(data:Entity){
        Data.insert(data)
    }
    suspend fun delete(data: Entity){
        Data.deletion(data)
    }

}