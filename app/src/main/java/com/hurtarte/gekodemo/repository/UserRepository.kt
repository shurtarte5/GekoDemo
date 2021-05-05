package com.hurtarte.gekodemo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.hurtarte.gekodemo.db.UserDao
import com.hurtarte.gekodemo.model.User

class UserRepository(private val userDao: UserDao) {

   // val readAllData: LiveData<List<User>> = userDao.readAllData("g","f")
   // val data : List<User> = userDao.login()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun login():  List<User>{
        var listausuario= userDao.login()


        return listausuario
    }

    fun readAllData(email:String,password:String):LiveData<List<User>>{
        var users = userDao.readAllData(email,password)
        return users
    }


}