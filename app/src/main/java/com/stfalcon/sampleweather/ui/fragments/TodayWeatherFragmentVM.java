package com.stfalcon.sampleweather.ui.fragments;

import android.util.Log;

import com.stfalcon.sampleweather.binding.fields.ObservableString;
import com.stfalcon.sampleweather.databinding.FragmentTodayWeatherBinding;
import com.stfalcon.sampleweather.models.WeatherResponse;
import com.stfalcon.sampleweather.network.sampleweather.SimpleWeatherClient;
import com.stfalcon.sampleweather.network.sampleweather.services.ApiEndpointInterface;
import com.stfalcon.sampleweather.ui.custom.base.binding.fragments.FragmentViewModel;

import retrofit2.Response;

/**
 * Created by artem on 16.03.16.
 */
public class TodayWeatherFragmentVM extends FragmentViewModel<TodayWeatherFragment, FragmentTodayWeatherBinding> {

    private static final String TAG = "TAG";
    public ObservableString temperature = new ObservableString();
    public ObservableString pressure = new ObservableString();
    public ObservableString humidity = new ObservableString();
    public ObservableString sky = new ObservableString();

    public TodayWeatherFragmentVM(TodayWeatherFragment fragment, FragmentTodayWeatherBinding binding) {
        super(fragment, binding);
    }

    @Override
    protected void initialize(FragmentTodayWeatherBinding binding) {
        new SimpleWeatherClient(getActivity()).getService(ApiEndpointInterface.class)
                .getWeather("London").enqueue(getActivity(), this::onWeatherResponse);
    }

    private void onWeatherResponse(Response<WeatherResponse> weatherResponseResponse) {
        Log.i(TAG, "onWeatherResponse: " + weatherResponseResponse.body().getMain().getTemp());
        Log.i(TAG, "onWeatherResponse: " + weatherResponseResponse.body().getMain().getPressure());
        Log.i(TAG, "onWeatherResponse: " + weatherResponseResponse.body().getMain().getHumidity());
        temperature.set(weatherResponseResponse.body().getMain().getTemp() + "");
        pressure.set(weatherResponseResponse.body().getMain().getPressure() + "");
        humidity.set(weatherResponseResponse.body().getMain().getHumidity() + "");
        sky.set(weatherResponseResponse.body().getWeather()[0].getDescription() + "");
    }
}
