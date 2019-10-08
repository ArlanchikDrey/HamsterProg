package ru.programminglearning.com.hamsterProg.Registration.Welcome;


import android.support.v4.app.Fragment;

import ru.programminglearning.com.hamsterProg.Registration.RegistrationActivityMain;

public class WelcomeActivity extends RegistrationActivityMain {

    @Override
    protected Fragment getFragment() {
        return WelcomeFragment.newInstance();
    }
}
