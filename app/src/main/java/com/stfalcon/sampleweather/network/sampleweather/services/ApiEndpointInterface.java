package com.stfalcon.sampleweather.network.sampleweather.services;

import com.stfalcon.sampleweather.BuildConfig;
import com.stfalcon.sampleweather.models.WeatherResponse;
import com.stfalcon.sampleweather.models.WeekWeatherResponse;
import com.stfalcon.sampleweather.network.custom.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by artem on 15.03.16.
 */
public interface ApiEndpointInterface {
    @GET("data/2.5/weather" + BuildConfig.API_KEY + BuildConfig.WEATHER_METRIC)
    Call<WeatherResponse> getWeather(@Query("q") String cityName);

    @GET("data/2.5/forecast/daily" + BuildConfig.API_KEY + BuildConfig.WEATHER_METRIC )
    Call<WeekWeatherResponse> getWeekWeather(@Query("q") String cityName);
}
