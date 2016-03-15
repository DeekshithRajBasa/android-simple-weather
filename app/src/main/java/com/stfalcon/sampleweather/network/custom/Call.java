package com.stfalcon.sampleweather.network.custom;

import android.content.Context;

/**
 * Created by troy379 on 11.01.16.
 */
public interface Call<T> extends retrofit2.Call<T> {

    void enqueue(Context context, CustomCallback.SuccessListener<T> onSuccessListener);
    void enqueue(Context context, CustomCallback.SuccessListener<T> onSuccessListener,
                 CustomCallback.FailureListener onFailureListener);

    Call<T> clone();
}
