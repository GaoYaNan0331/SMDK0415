package com.example.administrator.gaoyanan0415test.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;
/**
 * data:2017/4/15
 * author:高亚男(Administrator)
 * function:FragmentPagerAdapter 的设置
 */

public class MypagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<Fragment> list;
    private List<String> mlist;

    public MypagerAdapter(FragmentManager fm, Context context, List<Fragment> list, List<String> mlist) {
        super(fm);
        this.context = context;
        this.list = list;
        this.mlist = mlist;
    }

    public MypagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mlist.get(position);
    }
}
