package com.stfalcon.sampleweather.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by artem on 15.03.16.
 */
public interface ApiEndpointInterface {
    @GET("/weather?appid=b1b15e88fa797225412429c1c50c122a")
    Call<WeatherResponse> getWeather(@Query("q") String cityName);

}
