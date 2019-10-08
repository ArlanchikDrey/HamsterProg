package ru.programminglearning.com.hamsterProg.Registration;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.programminglearning.com.project123456.R;


public abstract class RegistrationActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reigistration_main);

        if (savedInstanceState == null){
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.registration_container,getFragment())
                    .commit();
        }

    }

    protected abstract Fragment getFragment();

}
