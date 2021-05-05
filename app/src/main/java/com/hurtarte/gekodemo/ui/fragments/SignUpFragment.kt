package com.hurtarte.gekodemo.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.hurtarte.gekodemo.R
import com.hurtarte.gekodemo.model.User
import com.hurtarte.gekodemo.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.fragment_signup.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern


class SignUpFragment: Fragment() {
    private lateinit var userVM:UserViewModel
    //lateinit var userList:List<User>

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        userVM= ViewModelProvider(this).get(UserViewModel::class.java)
//        //userList=userVM.login()
//
//    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootview= inflater.inflate(R.layout.fragment_signup,container,false)
       // val e = userList.size
       // Toast.makeText(requireContext(),"$e registros",Toast.LENGTH_LONG).show()
        var userList:List<User> = listOf()
        userVM= ViewModelProvider(this).get(UserViewModel::class.java)

//        userVM.readAllData.observe(viewLifecycleOwner, Observer { user ->
//            userList = user
//        })








       rootview.buttonCreateAccount.setOnClickListener {
            insertDataToDatabase()

       }
//        rootview.button.setOnClickListener {
//
//
//           // userVM = ViewModelProvider(this).get(UserViewModel::class.java)
//
////            userList=userVM.login()
////            userList= UserViewModel.listaUser
////            val e = userList.size
//            Toast.makeText(requireContext(),"${userList.get(0).phone}",Toast.LENGTH_LONG).show()
//
//       }






        return rootview
    }



    private fun insertDataToDatabase() {
        val name = editTextSingUpName.text.toString()







        val password = editTextSignUpPass.text.toString()
        val phone = editTextSignUpPhone.text.toString()
        val email  = editTextSignUpEmail.text.toString()
        if(inputCheck(name,email,password,phone)){
            if(mailVerification(email)){
                val user = User(0,name,email,password,phone)
                userVM.addUser(user)
                // userList=userVM.login()
                Toast.makeText(requireContext(),"User added successfully", Toast.LENGTH_LONG).show()
                clearFields()
            }else{
                editTextSignUpEmail.error = "Invalid Mail"
            }


        }else{
            Toast.makeText(requireContext(),"Fill out all fields", Toast.LENGTH_LONG).show()
        }

    }

    private fun mailVerification(mail:String): Boolean{
        val pattern:Pattern= Patterns.EMAIL_ADDRESS

        return pattern.matcher(mail).matches()
    }

    private fun inputCheck(name:String,email:String,password:String,phone:String):Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && TextUtils.isEmpty(password) && TextUtils.isEmpty(phone))
    }

    private fun clearFields(){
        editTextSingUpName.setText("")
        editTextSignUpEmail.setText("")
        editTextSignUpPass.setText("")
        editTextSignUpPhone.setText("")
    }
}