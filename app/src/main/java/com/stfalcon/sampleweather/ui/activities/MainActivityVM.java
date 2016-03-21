package com.stfalcon.sampleweather.ui.activities;

import android.support.design.widget.TabLayout;

import com.stfalcon.sampleweather.adapters.PagerAdapter;
import com.stfalcon.sampleweather.databinding.ActivityMainBinding;
import com.stfalcon.sampleweather.ui.custom.base.binding.activities.ActivityViewModel;

/**
 * Created by artem on 16.03.16.
 */
public class MainActivityVM extends ActivityViewModel<MainActivity, ActivityMainBinding> {

    public MainActivityVM(MainActivity activity, ActivityMainBinding binding) {
        super(activity, binding);
        initTabs();
    }

    private void initTabs() {

        getActivity().setSupportActionBar(getBinding().toolbar);

        TabLayout tabLayout = getBinding().tabLayout;
        tabLayout.addTab(tabLayout.newTab().setText("Today weather"));
        tabLayout.addTab(tabLayout.newTab().setText("Week weather"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerAdapter adapter = new PagerAdapter
                (getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        getBinding().pager.setAdapter(adapter);
        getBinding().pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getBinding().pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
