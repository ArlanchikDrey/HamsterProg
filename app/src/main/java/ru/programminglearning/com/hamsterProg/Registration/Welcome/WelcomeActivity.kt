package ru.programminglearning.com.hamsterProg.Registration.Welcome

import android.support.v4.app.Fragment
import ru.programminglearning.com.hamsterProg.Registration.RegistrationActivityMain

class WelcomeActivity : RegistrationActivityMain() {
    override val fragment: Fragment?
        get() =  WelcomeFragment.newInstance()
}