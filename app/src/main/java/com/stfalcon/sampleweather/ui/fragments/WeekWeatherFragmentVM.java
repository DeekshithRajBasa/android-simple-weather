package com.stfalcon.sampleweather.ui.fragments;


import android.util.Log;

import com.stfalcon.sampleweather.databinding.FragmentWeekWeatherBinding;
import com.stfalcon.sampleweather.models.WeekWeatherResponse;
import com.stfalcon.sampleweather.network.sampleweather.SimpleWeatherClient;
import com.stfalcon.sampleweather.network.sampleweather.services.ApiEndpointInterface;
import com.stfalcon.sampleweather.ui.custom.base.binding.fragments.FragmentViewModel;

import retrofit2.Response;

/**
 * Created by artem on 16.03.16.
 */
public class WeekWeatherFragmentVM extends FragmentViewModel<WeekWeatherFragment, FragmentWeekWeatherBinding> {

    private static final String TAG = "TAG_WEEK";

    public WeekWeatherFragmentVM(WeekWeatherFragment fragment, FragmentWeekWeatherBinding binding) {
        super(fragment, binding);
    }

    @Override
    protected void initialize(FragmentWeekWeatherBinding binding) {
        new SimpleWeatherClient(getActivity()).getService(ApiEndpointInterface.class)
                .getWeekWeather("London").enqueue(getActivity(), this::onWeekWeatherResponse);
    }

    private void onWeekWeatherResponse(Response<WeekWeatherResponse> weekWeatherResponseResponse) {
        Log.i(TAG, "onWeatherResponse: " + weekWeatherResponseResponse.body().getList().length);
    }

}