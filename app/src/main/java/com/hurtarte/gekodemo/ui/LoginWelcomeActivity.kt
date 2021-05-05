package com.hurtarte.gekodemo.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.hurtarte.gekodemo.R
import com.hurtarte.gekodemo.model.User
import com.hurtarte.gekodemo.ui.fragments.SignInFragment
import kotlinx.android.synthetic.main.activity_welcome.*

class LoginWelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val user = intent.extras?.get(SignInFragment.INTENT_USER_KEY) as User
        title="Welcome"


        textViewDetailUserName.text=user.name
        textViewDetailUserMail.text=user.email
        textViewDetailUserPhone.text=user.phone



        Glide.with(this).load("https://img.icons8.com/material-outlined/24/000000/shutdown.png").transform(CenterCrop()).into(imageView)

        imageView.setOnClickListener {


            finishAffinity()
        }



    }
}