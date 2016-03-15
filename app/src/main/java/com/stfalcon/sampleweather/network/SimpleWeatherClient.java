package com.stfalcon.sampleweather.network;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by artem on 15.03.16.
 */
public class SimpleWeatherClient implements Interceptor {

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_TYPE = "token ";

    private Retrofit retrofit;
    private Object service;
    private Context context;

    public SimpleWeatherClient(Context context) {
        this.context = context;

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(this)
                .addInterceptor(logging)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new CustomCallAdapterFactory(SimpleWeatherCallback.class))
                .build();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //User currentUser = Preferences.getManager().getCurrentUser(context);

        Request original = chain.request();
        /*HttpUrl.Builder urlBuilder = original.url().newBuilder();

        Request.Builder builder = original.newBuilder()
                .url(urlBuilder.build());
        if (currentUser != null && currentUser.getAccessToken() != null)
            builder = builder.header(HEADER_AUTHORIZATION,
                    AUTHORIZATION_TYPE + currentUser.getAccessToken());

        Request request = builder.build();*/

        return chain.proceed(original);
    }

    @SuppressWarnings("unchecked")
    public <T> T getService(Class<T> serviceClass) {
        if (service == null || !serviceClass.isInstance(service))
            service = retrofit.create(serviceClass);
        return (T) service;
    }

}
