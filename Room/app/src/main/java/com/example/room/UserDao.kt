package com.example.room

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :firstName")
    fun getUserName(firstName: String): User

    @Query("SELECT * FROM user WHERE id LIKE :uid")
    fun getUserID(uid: Int): User

    @Insert
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)

}