package ru.programminglearning.com.hamsterProg.BasicsContent.Functions;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import ru.programminglearning.com.hamsterProg.Basics.Adapter;
import ru.programminglearning.com.hamsterProg.Basics.ViewPagerAdapter;
import ru.programminglearning.com.hamsterProg.BasicsContent.TypeVariables.TypeActivity;
import ru.programminglearning.com.project123456.R;

public class FunctionsActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    public static ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functions);

        if (Build.VERSION.SDK_INT >= 21){
            setTranslucentStatus(true);
        }

        initTextHead();
        initViewPager();
        initTab();
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

    private void initTextHead(){
        Intent intent = getIntent();
        String s = intent.getStringExtra(Adapter.KEY);
        TextView textView = findViewById(R.id.textHeadFun);
        textView.setText(s);
    }

    private void initViewPager(){
        viewPager = findViewById(R.id.viewPagerFun);
        setViewPager(viewPager);
    }

    private void setViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentFunc());
        adapter.addFragment(new FragmentFuncTest());
        adapter.addFragment(new FragmentFunc2());
        adapter.addFragment(new FragmentFuncTest2());
        viewPager.setAdapter(adapter);
    }

    private void initTab(){
        tabLayout = findViewById(R.id.tablayoutFun);
        tabLayout.setupWithViewPager(viewPager);
        setIcons();
    }

    private void setIcons() {
        tabLayout.getTabAt(0).setIcon(TypeActivity.getIcons()[0]);
        tabLayout.getTabAt(1).setIcon(TypeActivity.getIcons()[1]);
        tabLayout.getTabAt(2).setIcon(TypeActivity.getIcons()[0]);
        tabLayout.getTabAt(3).setIcon(TypeActivity.getIcons()[1]);
    }
}
