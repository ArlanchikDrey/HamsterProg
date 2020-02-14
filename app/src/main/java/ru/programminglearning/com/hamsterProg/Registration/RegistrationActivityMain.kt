package ru.programminglearning.com.hamsterProg.Registration

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import ru.programminglearning.com.project123456.R

abstract class RegistrationActivityMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reigistration_main)
        if (savedInstanceState == null) {
            val manager = supportFragmentManager
            manager.beginTransaction()
                    .replace(R.id.registration_container, fragment!!)
                    .commit()
        }
    }

    protected abstract val fragment: Fragment?
}