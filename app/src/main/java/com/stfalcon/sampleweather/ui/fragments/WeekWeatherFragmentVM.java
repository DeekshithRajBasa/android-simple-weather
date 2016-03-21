package com.stfalcon.sampleweather.ui.fragments;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

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

        new SimpleWeatherClient(getActivity()).getService(ApiEndpointInterface.class)
                .getWeekWeather("London").enqueue(getActivity(), this::onWeekWeatherResponse);
    }

    private void onWeekWeatherResponse(Response<WeekWeatherResponse> weekWeatherResponseResponse) {
        adapter.setItems(weekWeatherResponseResponse.body().getList());
    }

    private void initAdapter() {
        adapter.setItemLayout(R.layout.item_day_of_week);
        adapter.setVariable(BR.weather);
    }

    @Subscribe
    public void onLocationChangeEvent(LocationChangeEvent event){
        Toast.makeText(getActivity(), event.currentLocation.toString(), Toast.LENGTH_LONG).show();
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