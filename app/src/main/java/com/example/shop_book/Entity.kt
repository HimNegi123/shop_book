package com.example.shop_book

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_data")
data class Entity(
    @ColumnInfo(name="particular")val part:String, @ColumnInfo(name="mrp")val mrp :String,
    @ColumnInfo(name="buy on")val buy:String,
    @ColumnInfo(name="sell  on")val sell:String,
    @PrimaryKey(autoGenerate = true)val id: Int =0

    )
