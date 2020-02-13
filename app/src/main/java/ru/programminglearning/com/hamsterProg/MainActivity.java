package ru.programminglearning.com.hamsterProg;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import ru.programminglearning.com.hamsterProg.Basics.BasicActivity;
import ru.programminglearning.com.project123456.R;
import ru.programminglearning.com.hamsterProg.Registration.Welcome.WelcomeActivity;

public class MainActivity extends AppCompatActivity{

    private FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isOnline(this)){
            if(currentUser==null){
                startActivity(WelcomeActivity.class);
            }else{
                anim();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(BasicActivity.class);
                    }
                },2400);
            }
        }else{

        }
    }

    private void setTranslucentStatus(boolean b) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (b) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void anim(){
        ImageView imageView = findViewById(R.id.splashView);
        imageView.animate().setDuration(2000).rotation(360);
    }
    private void startActivity(Class<?> classes) {
        Intent intent = new Intent
                (MainActivity.this, classes);
        startActivity(intent);
        finish();
    }

    /**метод для проверки наличия подключения к сети*/
    public boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, "Нет подключения к интернету:( "+"\n"+"Проверьте связь и перезайдите в приложение", Toast.LENGTH_SHORT).show();

        return false;
    }

}



