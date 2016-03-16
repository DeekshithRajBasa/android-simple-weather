package com.stfalcon.sampleweather.ui.activities;

import android.os.Bundle;

import com.stfalcon.sampleweather.R;
import com.stfalcon.sampleweather.databinding.ActivityMainBinding;
import com.stfalcon.sampleweather.ui.custom.base.binding.activities.BindingActivity;

public class MainActivity extends BindingActivity<ActivityMainBinding, MainActivityVM> {

    private static final int LAYOUT = R.layout.activity_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(LAYOUT);

    }


    @Override
    public MainActivityVM createViewModel() {
        return new MainActivityVM(this, getBinding());
    }
}
