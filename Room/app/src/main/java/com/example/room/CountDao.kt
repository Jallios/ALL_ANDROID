package com.example.room

import androidx.room.*

@Dao
interface CountDao {
    @Query("SELECT * FROM count")
    fun getAll(): List<Count>

    @Query("SELECT count FROM count where userId = :uid and act =:a and category =:c")
    fun getUid(uid:Int, a :Int,c:Int): List<Count>

    @Insert
    fun insertCount(count: Count)

    @Delete
    fun deleteCount(count: Count)

    @Update
    fun updateCount(count: Count)

}