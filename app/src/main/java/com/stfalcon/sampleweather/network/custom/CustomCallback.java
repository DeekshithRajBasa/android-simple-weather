package com.stfalcon.sampleweather.network.custom;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.stfalcon.sampleweather.utils.AppUtilities;

import java.net.UnknownHostException;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by troy379 on 11.01.16.
 */
public abstract class CustomCallback<T> implements Callback<T> {

    private Context context;
    private Call<T> call;
    private SuccessListener<T> onSuccessListener;
    private FailureListener onFailureListener;

    private CustomCallback() {
    }

    protected CustomCallback(Context context, Call<T> call, SuccessListener<T> onSuccessListener) {
        this(context, call, onSuccessListener, null);
    }

    protected CustomCallback(Context context, Call<T> call,
                             SuccessListener<T> onSuccessListener, FailureListener onFailureListener) {
        this.context = context;
        this.onSuccessListener = onSuccessListener;
        this.onFailureListener = onFailureListener;
        this.call = call;
    }


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        executeSuccess(response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        //TODO handle exceptions for different cases
        if (t instanceof UnknownHostException) {
            execute(() -> AppUtilities.showToast(context, "Check connection to Internet", true));
        } else if (onFailureListener == null) {
            execute(() -> {
                AppUtilities.showToast(context, "Houston, we have a problem " + t.getMessage(), false);
                Log.i("TAG", "onFailure: " + t.getMessage());
            });
        } else executeFailure(t);
    }


    protected void executeSuccess(Response<T> response) {
        execute(() -> onSuccessListener.onSuccess(response));
    }

    protected void executeFailure(Throwable t) {
        if (onFailureListener != null)
            execute(() -> onFailureListener.onFailure(t));
    }

    protected void execute(Runnable runnable) {
        new MainThreadExecutor().execute(runnable);
    }


    public Call<T> getCall() {
        return call;
    }

    public Context getContext() {
        return context;
    }

    public interface SuccessListener<T> {
        void onSuccess(Response<T> response);
    }

    public interface FailureListener {
        void onFailure(Throwable t);
    }


    public static class MainThreadExecutor implements Executor {
        private final Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable r) {
            handler.post(r);
        }
    }
}
