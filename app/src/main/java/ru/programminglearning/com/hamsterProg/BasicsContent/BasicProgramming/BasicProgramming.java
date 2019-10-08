package ru.programminglearning.com.hamsterProg.BasicsContent.BasicProgramming;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import ru.programminglearning.com.hamsterProg.Basics.Adapter;
import ru.programminglearning.com.hamsterProg.Basics.ViewPagerAdapter;
import ru.programminglearning.com.project123456.R;


public class BasicProgramming extends AppCompatActivity {
    private TabLayout tabLayout;
    protected static ViewPager viewPager; //статик, так как юзаем ее еще в слушателе кнопки фрагмента InfoFragment
    private TextView textView;
    private Boolean isSound = true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_programming);

        if (Build.VERSION.SDK_INT >= 21) {
            setTranslucentStatus(true);
        }
        getTextHead();

        viewPager = findViewById(R.id.viewPagerBasicProgramming);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tablayoutBasic);
        tabLayout.setupWithViewPager(viewPager);
        setTubIcons();
        onClickSoundView();

    }

    private void getTextHead(){
        Intent intent = getIntent();
        String headText = intent.getStringExtra(Adapter.KEY);
        textView = findViewById(R.id.textHead);
        textView.setText(headText);
    }

    private void setTubIcons() {
        tabLayout.getTabAt(0).setIcon(getIcons()[0]);
        tabLayout.getTabAt(1).setIcon(getIcons()[1]);
    }

    private int[] getIcons(){
        int[] tabIcons = {R.drawable.icon_triangle,
                R.drawable.ic_question};
        return tabIcons;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new InfoFragment());
        adapter.addFragment(new TestFragment());
        viewPager.setAdapter(adapter);
    }

    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void onClickSoundView(){
        final ImageView view = findViewById(R.id.imageSound);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSound){
                    InfoFragment.mTTS.shutdown();
                    InfoFragment.mTTS.stop();
                    view.setImageResource(R.drawable.ic_sound_off_24dp);
                    isSound = false;
                }else{
                    view.setImageResource(R.drawable.ic_sound_on_24dp);
                    isSound = true;
                }

            }
        });
    }

}
