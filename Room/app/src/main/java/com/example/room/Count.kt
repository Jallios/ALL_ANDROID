package com.example.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "count")
data class Count(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "category") val category: Int?,
    @ColumnInfo(name = "act") val act: Int?,
    @ColumnInfo(name = "count") val count: Double?,
    @ColumnInfo(name = "userId") val userId: Int?


)