package com.stfalcon.sampleweather.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.stfalcon.sampleweather.ui.fragments.TodayWeatherFragment;
import com.stfalcon.sampleweather.ui.fragments.WeekWeatherFragment;

/**
 * Created by artem on 15.03.16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TodayWeatherFragment tab1 = new TodayWeatherFragment();
                return tab1;
            case 1:
                WeekWeatherFragment tab2 = new WeekWeatherFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}