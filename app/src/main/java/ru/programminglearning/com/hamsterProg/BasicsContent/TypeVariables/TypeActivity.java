package ru.programminglearning.com.hamsterProg.BasicsContent.TypeVariables;

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
import ru.programminglearning.com.project123456.R;

public class TypeActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    public static ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        if (Build.VERSION.SDK_INT >= 21) {
            setTranslucentStatus(true);
        }

        initViewPager();
        initTab();
        getHead();
    }

    private void getHead(){
        TextView textView = findViewById(R.id.textHeadType);
        Intent intent = getIntent();
        String name = intent.getStringExtra(Adapter.KEY);
        textView.setText(name);

    }

    private void initTab(){
        tabLayout = findViewById(R.id.tablayoutType);
        tabLayout.setupWithViewPager(pager);
        setTubIcons();
    }

    private void setTubIcons() {
        tabLayout.getTabAt(0).setIcon(getIcons()[0]);
        tabLayout.getTabAt(1).setIcon(getIcons()[1]);
        tabLayout.getTabAt(2).setIcon(getIcons()[0]);
        tabLayout.getTabAt(3).setIcon(getIcons()[1]);
    }

    public static int[] getIcons(){
        int[] tabIcons = {R.drawable.icon_triangle,
                R.drawable.ic_question
        };
        return tabIcons;
    }

    private void initViewPager(){
        pager = findViewById(R.id.viewPagerType);
        setupViewPager(pager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TypeFragmentDescription1());
        adapter.addFragment(new TypeFragmentTest1());
        adapter.addFragment(new TypeFragmentDescription2());
        adapter.addFragment(new TypeFragmentTest2());
        viewPager.setAdapter(adapter);
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
