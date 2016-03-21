package com.stfalcon.sampleweather.network.sampleweather;

import android.content.Context;

import com.stfalcon.sampleweather.network.Status;
import com.stfalcon.sampleweather.network.custom.CustomCallback;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by artem on 15.03.16.
 */
public class SimpleWeatherCallback<T> extends CustomCallback<T> {

    public SimpleWeatherCallback(Context context, Call<T> call, SuccessListener<T> onSuccessListener,
                                 CustomCallback.FailureListener onFailureListener) {
        super(context, call, onSuccessListener, onFailureListener);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccess()) {
            super.onResponse(call, response);
        } else {
            switch (Status.fromInt(response.code())) {
                case API_ERROR:
                    retry(call);
                    break;
                default:
                    super.onFailure(call, new Throwable(response.message()));
            }
        }
    }

    private void retry(Call<T> call) {
        call.enqueue(this);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        super.onFailure(call, t);
    }
}