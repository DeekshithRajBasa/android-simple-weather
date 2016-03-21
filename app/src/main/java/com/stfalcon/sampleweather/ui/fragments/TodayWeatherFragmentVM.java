package com.stfalcon.sampleweather.ui.fragments;

import com.stfalcon.sampleweather.binding.fields.ObservableBoolean;
import com.stfalcon.sampleweather.binding.fields.ObservableFloat;
import com.stfalcon.sampleweather.binding.fields.ObservableString;
import com.stfalcon.sampleweather.databinding.FragmentTodayWeatherBinding;
import com.stfalcon.sampleweather.models.LocationChangeEvent;
import com.stfalcon.sampleweather.models.WeatherResponse;
import com.stfalcon.sampleweather.network.sampleweather.SimpleWeatherClient;
import com.stfalcon.sampleweather.network.sampleweather.services.ApiEndpointInterface;
import com.stfalcon.sampleweather.ui.custom.base.binding.fragments.FragmentViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import retrofit2.Response;

/**
 * Created by artem on 16.03.16.
 */
public class TodayWeatherFragmentVM extends FragmentViewModel<TodayWeatherFragment, FragmentTodayWeatherBinding> {

    private static final String TAG = "TAG";
    public ObservableFloat temperature = new ObservableFloat();
    public ObservableString pressure = new ObservableString();
    public ObservableString humidity = new ObservableString();
    public ObservableString sky = new ObservableString();
    public ObservableString city = new ObservableString();
    public ObservableString skyIco = new ObservableString();
    public ObservableBoolean isLoading = new ObservableBoolean(true);

    public TodayWeatherFragmentVM(TodayWeatherFragment fragment, FragmentTodayWeatherBinding binding) {
        super(fragment, binding);
    }

    @Override
    protected void initialize(FragmentTodayWeatherBinding binding) {
    }

    @Subscribe
    public void onLocationChangeEvent(LocationChangeEvent event){
        new SimpleWeatherClient(getActivity()).getService(ApiEndpointInterface.class)
                .getWeather(event.currentLocation.getLatitude(),
                        event.currentLocation.getLongitude()).enqueue(getActivity(), this::onWeatherResponse);
    }

    private void onWeatherResponse(Response<WeatherResponse> weatherResponseResponse) {
        temperature.set(weatherResponseResponse.body().getMain().getTemp());
        pressure.set(weatherResponseResponse.body().getMain().getPressure() + "");
        humidity.set(weatherResponseResponse.body().getMain().getHumidity() + "");
        sky.set(weatherResponseResponse.body().getWeather()[0].getDescription() + "");
        skyIco.set(weatherResponseResponse.body().getWeather()[0].getIcon() + "");
        city.set(weatherResponseResponse.body().getName());
        isLoading.set(false);
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
    }
}
