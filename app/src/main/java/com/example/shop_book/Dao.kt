package com.example.shop_book

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface dataDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(data: Entity)
    @Delete
    suspend fun  deletion(data: Entity)
    @Query("Select * From table_data order by id ASC")
    fun getData(): LiveData<List<Entity>>

}