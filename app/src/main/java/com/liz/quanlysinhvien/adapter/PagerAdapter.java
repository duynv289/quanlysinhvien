package com.liz.quanlysinhvien.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.liz.quanlysinhvien.R;
import com.liz.quanlysinhvien.fragment.FragmentEntry;
import com.liz.quanlysinhvien.fragment.FragmentStudent;

/**
 * Created by Administrator on 10/7/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final int PAGER_NUMBER = 2;
    private static final String ENTRY = "Nhập thông tin";
    private static final String SHOW = "Hiển thị thông tin";
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case INDEX_ZERO:
                fragment = FragmentEntry.getNewInstance();
                break;
            case INDEX_ONE:
                fragment = FragmentStudent.getNewInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGER_NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case INDEX_ZERO :
                title = ENTRY;
                break;
            case INDEX_ONE :
                title = SHOW;
                break;
        }
        return title;
    }
}
