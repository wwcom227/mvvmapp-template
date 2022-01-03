package com.template.mvvmapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.template.mvvmapp.client.ui.ClientMainActivity

class LauncherActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, ClientMainActivity::class.java)
        startActivity(intent)
        finish()
    }
}