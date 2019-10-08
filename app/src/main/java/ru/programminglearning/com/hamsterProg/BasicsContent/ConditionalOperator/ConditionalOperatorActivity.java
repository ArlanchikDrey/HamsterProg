package ru.programminglearning.com.hamsterProg.BasicsContent.ConditionalOperator;

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

public class ConditionalOperatorActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    public static ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditional_operator);

        if (Build.VERSION.SDK_INT >= 21){
            setTranslucentStatus(true);
        }
        setHeadText();
        initViewPager();
        initTab();
    }

    private void setHeadText(){
        TextView textView = findViewById(R.id.textHeadCond);
        Intent intent = getIntent();
        String s = intent.getStringExtra(Adapter.KEY);
        textView.setText(s);
    }

    private void initViewPager(){
        pager = findViewById(R.id.viewPagerCond);
        setViewPagerAdapter(pager);
    }

    private void setViewPagerAdapter(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ConditionalFragmentDesc());
        adapter.addFragment(new ConditionalFragmentTest());
        viewPager.setAdapter(adapter);
    }

    private void initTab(){
        tabLayout = findViewById(R.id.tablayoutCond);
        tabLayout.setupWithViewPager(pager);
        setIconTab();
    }

    private void setIconTab(){
        tabLayout.getTabAt(0).setIcon(TypeActivity.getIcons()[0]);
        tabLayout.getTabAt(1).setIcon(TypeActivity.getIcons()[1]);
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
}
