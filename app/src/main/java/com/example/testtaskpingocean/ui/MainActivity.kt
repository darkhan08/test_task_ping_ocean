package com.example.testtaskpingocean.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testtaskpingocean.R
import com.example.testtaskpingocean.ui.login.LoginFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment: LoginFragment = LoginFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment)
            .addToBackStack(null).commit()
    }
}
