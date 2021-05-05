package com.hurtarte.gekodemo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hurtarte.gekodemo.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table WHERE email = :email AND password = :password")
    fun readAllData(email:String,password:String): LiveData<List<User>>

    @Query("SELECT * FROM user_table")

    suspend fun login(): List<User>

    // WHERE email = :email AND password = :password
    //email:String,password:String
}