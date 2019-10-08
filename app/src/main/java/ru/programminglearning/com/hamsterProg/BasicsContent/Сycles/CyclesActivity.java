package ru.programminglearning.com.hamsterProg.BasicsContent.Сycles;

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

public class CyclesActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    public static ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycles);

        if (Build.VERSION.SDK_INT >= 21){
            setTranslucentStatus(true);
        }

        initTextHead();
        initViewPager();
        initTab();
    }

    private void initTextHead(){
        Intent intent = getIntent();
        String s = intent.getStringExtra(Adapter.KEY);

        TextView textView = findViewById(R.id.textHeadLoop);
        textView.setText(s);
    }

    private void initViewPager(){
        viewPager = findViewById(R.id.viewPagerLoop);
        setViewPager(viewPager);
    }

    private void setViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentCycleDescWhile());
        adapter.addFragment(new FragmentCycleTestWhile());
        adapter.addFragment(new FragmentCycleDescFor());
        adapter.addFragment(new FragmentCycleTestFor());
        viewPager.setAdapter(adapter);
    }

    private void initTab(){
        tabLayout = findViewById(R.id.tablayoutLoop);
        tabLayout.setupWithViewPager(viewPager);
        setIcons();
    }

    private void setIcons() {
        tabLayout.getTabAt(0).setIcon(TypeActivity.getIcons()[0]);
        tabLayout.getTabAt(1).setIcon(TypeActivity.getIcons()[1]);
        tabLayout.getTabAt(2).setIcon(TypeActivity.getIcons()[0]);
        tabLayout.getTabAt(3).setIcon(TypeActivity.getIcons()[1]);
    }

    /**
     * Этот метод делает верхний бар под цвет тула*/
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
}
