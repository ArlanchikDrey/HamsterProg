package ru.programminglearning.com.hamsterProg.Basics

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import java.util.*

class ViewPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    private val mFragmentTitle: MutableList<String> = ArrayList()
    private val mFragmentList: MutableList<Fragment> = ArrayList()

    override fun getPageTitle(position: Int): CharSequence? {
        return if (mFragmentTitle.size == 0) {
            null
        } else {
            mFragmentTitle[position]
        }
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentTitle.add(title)
        mFragmentList.add(fragment)
    }

    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)
    }

    override fun getItem(i: Int): Fragment {
        return mFragmentList[i]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }
}