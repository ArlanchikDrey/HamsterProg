package ru.programminglearning.com.hamsterProg.BasicsContent.MathOperator;

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

public class MathOperatorActivity extends AppCompatActivity {
    private TabLayout layout;
    public static ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_operator);

        if (Build.VERSION.SDK_INT >= 21){
            setTranslucentStatus(true);
        }

        initViewPager();
        initTabLayout();
        getHead();
    }

    private void getHead(){
        TextView textView = findViewById(R.id.textHeadMath);
        Intent intent = getIntent();
        String s = intent.getStringExtra(Adapter.KEY);
        textView.setText(s);

    }

    private void initViewPager(){
        pager = findViewById(R.id.viewPagerMath);
        setViewPager(pager);
    }

    private void setViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentMathDesc());
        adapter.addFragment(new FragmentMathTest());
        adapter.addFragment(new FragmentMathDescIncre());
        adapter.addFragment(new FragmentMathTestIncre());
        viewPager.setAdapter(adapter);
    }

    private void initTabLayout(){
        layout = findViewById(R.id.tablayoutMath);
        layout.setupWithViewPager(pager);
        setIconTab();
    }

    private void setIconTab(){
        layout.getTabAt(0).setIcon(TypeActivity.getIcons()[0]);
        layout.getTabAt(1).setIcon(TypeActivity.getIcons()[1]);
        layout.getTabAt(2).setIcon(TypeActivity.getIcons()[0]);
        layout.getTabAt(3).setIcon(TypeActivity.getIcons()[1]);
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
