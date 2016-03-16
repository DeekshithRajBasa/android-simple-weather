package com.stfalcon.sampleweather.ui.fragments;

import com.stfalcon.sampleweather.R;
import com.stfalcon.sampleweather.databinding.FragmentTodayWeatherBinding;
import com.stfalcon.sampleweather.ui.custom.base.binding.fragments.BindingFragment;

/**
 * Created by artem on 15.03.16.
 */
public class TodayWeatherFragment extends BindingFragment<TodayWeatherFragmentVM, FragmentTodayWeatherBinding> {

    private static final int LAYOUT = R.layout.fragment_today_weather;

    public TodayWeatherFragment() {  }

    @Override
    protected int onCreateView() {
        return LAYOUT;
    }

    @Override
    protected TodayWeatherFragmentVM onCreateViewModel(FragmentTodayWeatherBinding binding) {
        return new TodayWeatherFragmentVM(this, binding);
    }

    @Override
    protected void onViewCreated(TodayWeatherFragmentVM viewModel) {
        getBinding().setViewModel(viewModel);
    }

}
