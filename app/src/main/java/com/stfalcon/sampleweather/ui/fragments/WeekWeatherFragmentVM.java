package com.stfalcon.sampleweather.ui.fragments;


import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.stfalcon.sampleweather.BR;
import com.stfalcon.sampleweather.R;
import com.stfalcon.sampleweather.adapters.RecyclerBindingAdapter;
import com.stfalcon.sampleweather.databinding.FragmentWeekWeatherBinding;
import com.stfalcon.sampleweather.models.DayOfWeekWeather;
import com.stfalcon.sampleweather.models.LocationChangeEvent;
import com.stfalcon.sampleweather.models.WeekWeatherResponse;
import com.stfalcon.sampleweather.network.sampleweather.SimpleWeatherClient;
import com.stfalcon.sampleweather.network.sampleweather.services.ApiEndpointInterface;
import com.stfalcon.sampleweather.ui.custom.base.binding.fragments.FragmentViewModel;
import com.stfalcon.sampleweather.utils.DividerItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import retrofit2.Response;

/**
 * Created by artem on 16.03.16.
 */
public class WeekWeatherFragmentVM extends FragmentViewModel<WeekWeatherFragment, FragmentWeekWeatherBinding> {

    private static final String TAG = "TAG_WEEK";
    private static final int DELAY_LENGTH = 1000;
    public RecyclerBindingAdapter<DayOfWeekWeather> adapter = new RecyclerBindingAdapter<>();
    public LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    public RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getActivity()
            .getResources().getDrawable(R.drawable.shape_divider), false, false);

    public WeekWeatherFragmentVM(WeekWeatherFragment fragment, FragmentWeekWeatherBinding binding) {
        super(fragment, binding);
        initAdapter();
    }

    @Override
    protected void initialize(FragmentWeekWeatherBinding binding) {

    }

    private void initAdapter() {
        adapter.setItemLayout(R.layout.item_day_of_week);
        adapter.setVariable(BR.weather);
    }

    @Subscribe
    public void onLocationChangeEvent(LocationChangeEvent event){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new SimpleWeatherClient(getActivity()).getService(ApiEndpointInterface.class)
                        .getWeekWeather(event.currentLocation.getLatitude(),
                                event.currentLocation.getLongitude())
                        .enqueue(getActivity(), this::onWeekWeatherResponse);
            }

            private void onWeekWeatherResponse(Response<WeekWeatherResponse> weekWeatherResponseResponse) {
                adapter.setItems(weekWeatherResponseResponse.body().getList());
            }
        }, DELAY_LENGTH);
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