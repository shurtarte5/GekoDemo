package com.hurtarte.gekodemo.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hurtarte.gekodemo.R
import com.hurtarte.gekodemo.model.User
import com.hurtarte.gekodemo.ui.LoginWelcomeActivity
import com.hurtarte.gekodemo.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class SignInFragment : Fragment() {

    var userList: List<User> = listOf()



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        var rootview = inflater.inflate(R.layout.fragment_login, container, false)


        rootview.buttonLogin.setOnClickListener {


            if(rootview.editTextEmail.text.isNotEmpty()&&rootview.editTextPassword.text.isNotEmpty()){
                var correo = rootview.editTextEmail.text.toString()
                var contra = rootview.editTextPassword.text.toString()
                var userVM = ViewModelProvider(this).get(UserViewModel::class.java)


                userVM.read(correo, contra).observe(viewLifecycleOwner, Observer { user ->
                    //userList = user
                    if(user.size!=0){
                        if(correo==user.get(0).email && contra == user.get(0).password){
                            val intent= Intent(requireContext(),LoginWelcomeActivity::class.java)

                            intent.putExtra(INTENT_USER_KEY, user[0])
                            startActivity(intent)

                        }
                    }else{
                        Toast.makeText(requireContext(), "User does not exist", Toast.LENGTH_LONG).show()
                    }



                })

            }else{
                Toast.makeText(requireContext(), "Both fields must be filled", Toast.LENGTH_LONG).show()
            }





        }




        return rootview
    }



    companion object{
        const val INTENT_USER_KEY="user"
    }



}