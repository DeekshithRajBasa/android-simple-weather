package com.stfalcon.sampleweather.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stfalcon.sampleweather.R;
import com.stfalcon.sampleweather.databinding.FragmentOneBinding;
import com.stfalcon.sampleweather.network.ApiEndpointInterface;
import com.stfalcon.sampleweather.network.SimpleWeatherClient;
import com.stfalcon.sampleweather.network.WeatherResponse;

import retrofit2.Response;

/**
 * Created by artem on 15.03.16.
 */
public class OneFragment extends Fragment {
    private static final String TAG = "TAG";

    public OneFragment() {
        // Required empty public constructor
    }
    FragmentOneBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_one, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new SimpleWeatherClient(getContext()).getService(ApiEndpointInterface.class).getWeather("London").enqueue(new CustomCallback<WeatherResponse>() {
            @Override
            protected void executeSuccess(Response<WeatherResponse> response) {
                super.executeSuccess(response);
                Log.i(TAG, "executeSuccess: " + response.body().getMain().getTemp());
                Log.i(TAG, "executeSuccess: " + response.body().getMain().getPressure());
                Log.i(TAG, "executeSuccess: " + response.body().getMain().getHumidity());
            }
        });
    }
}
