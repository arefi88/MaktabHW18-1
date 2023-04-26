package com.example.maktabhw18_1.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.maktabhw18_1.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}