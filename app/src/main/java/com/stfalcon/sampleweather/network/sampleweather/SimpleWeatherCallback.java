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

    private void onUnauthorized() {
//        new SimpleWeatherClient(getContext()).getService(UserService.class)
//                .getCurrent().enqueue(getContext(),
//                response -> {
//                    Preferences.getManager().setCurrentUser(getContext(), response.body());
//                    getCall().clone().enqueue(this);
//                });
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccess()) {
            super.onResponse(call, response);
        } else {
            switch (Status.fromInt(response.code())) {
                case UNAUTHORIZED:
                    onUnauthorized();
                    break;
                default:
                    super.onFailure(call, new Throwable(response.message()));
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        super.onFailure(call, t);
    }
}