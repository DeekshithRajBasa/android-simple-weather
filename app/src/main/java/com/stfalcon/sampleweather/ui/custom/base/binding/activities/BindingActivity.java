package com.stfalcon.sampleweather.ui.custom.base.binding.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import com.stfalcon.sampleweather.BR;
import com.stfalcon.sampleweather.ui.custom.base.BaseActivity;


/**
 * Created by troy379 on 21.01.16.
 */
public abstract class BindingActivity<B extends ViewDataBinding, VM extends ActivityViewModel> extends BaseActivity {

    private B binding;
    private VM viewModel;

    public void bind(int layoutResID) {
        binding = DataBindingUtil.setContentView(this, layoutResID);
        this.viewModel = viewModel == null ? createViewModel() : viewModel;
        binding.setVariable(BR.viewModel, viewModel);
    }

    public B getBinding() {
        return binding;
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.onStart();
    }

    @Override
    protected void onStop() {
        viewModel.onStop();
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewModel.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        viewModel.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        viewModel.onBackKeyPress();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }

    public abstract VM createViewModel();

    public VM getViewModel() {
        return viewModel;
    }
}
