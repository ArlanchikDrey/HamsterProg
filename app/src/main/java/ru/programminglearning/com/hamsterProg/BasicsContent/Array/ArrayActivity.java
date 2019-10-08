package ru.programminglearning.com.hamsterProg.BasicsContent.Array;

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

public class ArrayActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    public static ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array);

        initHeadText();

        if (Build.VERSION.SDK_INT >= 21){
            setTranslucentStatus(true);
        }

        initViewPager();
        initTabLayout();

    }

    private void initViewPager(){
        viewPager = findViewById(R.id.viewPagerArray);
        setViewPager(viewPager);
    }

    private void setViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentArray());
        adapter.addFragment(new FragmentArrayTest());
        viewPager.setAdapter(adapter);
    }

    private void initTabLayout(){
        tabLayout = findViewById(R.id.tablayoutArray);
        tabLayout.setupWithViewPager(viewPager);
        setIcons();
    }

    private void setIcons() {
        tabLayout.getTabAt(0).setIcon(TypeActivity.getIcons()[0]);
        tabLayout.getTabAt(1).setIcon(TypeActivity.getIcons()[1]);
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

    private void initHeadText(){
        Intent intent = getIntent();
        String s = intent.getStringExtra(Adapter.KEY);

        TextView textView = findViewById(R.id.textHeadArray);
        textView.setText(s);
    }
}
