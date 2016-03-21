package com.stfalcon.sampleweather.ui.custom.base.binding.fragments;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by troy379 on 05.01.16.
 */
public abstract class BindingFragment<VM extends FragmentViewModel, B extends ViewDataBinding>
        extends Fragment {

    protected abstract int onCreateView();
    protected abstract VM onCreateViewModel(B binding);
    protected abstract void onViewCreated(VM viewModel);

    private B binding;
    private VM viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, onCreateView(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewCreated(getUpdatedViewModel());
    }

    public B getBinding() {
        return binding;
    }

    @SuppressWarnings("unchecked")
    private VM getUpdatedViewModel() {
        if (viewModel == null) viewModel = onCreateViewModel(binding);
        else viewModel.updateBinding(binding);
        return viewModel;
    }

    public VM getViewModel() {
        return viewModel;
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.onPause();
    }

    @Override
    public void onStop() {
        super.onPause();
        viewModel.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.onStart();
    }
}
