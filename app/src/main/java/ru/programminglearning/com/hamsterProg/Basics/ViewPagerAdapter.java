package ru.programminglearning.com.hamsterProg.Basics;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter{

    private final List<String> mFragmentTitle = new ArrayList<>();
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (mFragmentTitle.size() == 0){
            return null;
        }else {
            return mFragmentTitle.get(position);
        }
    }

    public void addFragment(Fragment fragment, String title){
        mFragmentTitle.add(title);
        mFragmentList.add(fragment);
    }

    public void addFragment(Fragment fragment){
        mFragmentList.add(fragment);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
