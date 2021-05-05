package com.hurtarte.gekodemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hurtarte.gekodemo.db.UserDatabase
import com.hurtarte.gekodemo.model.User
import com.hurtarte.gekodemo.repository.UserRepository
import kotlinx.coroutines.*

class UserViewModel(application: Application) : AndroidViewModel(application) {
    //val readAllData: LiveData<List<User>>
    private val repository: UserRepository
   // lateinit var listaUser: List<User>
    //val listaUser:List<User> = listOf()



    //val data:List<User>
    // var user: List<User>

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        //readAllData = repository.readAllData("g","f")
        // viewModelScope.launch(Dispatchers.IO) {
        //  listausuario= repository.login()
        //}
        //readAllData = repository.readAllData


    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }


   fun read(email:String,password:String):LiveData<List<User>>{
       return repository.readAllData(email,password)
   }



    //email:String, password:String


}

