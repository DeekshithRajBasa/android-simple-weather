package com.stfalcon.sampleweather.ui.custom.base.binding.activities;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;

import com.stfalcon.sampleweather.ui.custom.base.BaseActivity;

import io.realm.Realm;

/**
 * Created by alex on 19.01.16.
 */
public abstract class ActivityViewModel<A extends BaseActivity, B extends ViewDataBinding>
        extends BaseObservable {

    private A activity;
    private B binding;
    private Realm realm;

    public ActivityViewModel(A activity, B binding) {
        this.activity = activity;
        this.binding = binding;
        realm = Realm.getDefaultInstance();
    }

    public A getActivity() {
        return activity;
    }

    public B getBinding() {
        return binding;
    }

    public void finish() {
        activity.finish();
    }

    public void onBackKeyPress() {

    }

    public void onStart() {

    }

    public void onStop() {

    }

    public void onPause() {

    }


    public void onResume() {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public void onDestroy() {
        realm.close();
    }

    public Realm getRealm() {
        return realm;
    }


}