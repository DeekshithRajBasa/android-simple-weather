package com.stfalcon.sampleweather.ui.fragments;

import com.stfalcon.sampleweather.R;
import com.stfalcon.sampleweather.databinding.FragmentWeekWeatherBinding;
import com.stfalcon.sampleweather.ui.custom.base.binding.fragments.BindingFragment;

/**
 * Created by artem on 16.03.16.
 */
public class WeekWeatherFragment extends BindingFragment<WeekWeatherFragmentVM, FragmentWeekWeatherBinding> {

    private static final int LAYOUT = R.layout.fragment_week_weather;

    public WeekWeatherFragment() {  }

    @Override
    protected int onCreateView() {
        return LAYOUT;
    }

    @Override
    protected WeekWeatherFragmentVM onCreateViewModel(FragmentWeekWeatherBinding binding) {
        return new WeekWeatherFragmentVM(this, binding);
    }

    @Override
    protected void onViewCreated(WeekWeatherFragmentVM viewModel) {
        getBinding().setViewModel(viewModel);
    }

}
