package com.stfalcon.sampleweather.binding.fields;

import android.databinding.BaseObservable;
import android.databinding.BindingConversion;

/**
 * Created by troy379 on 22.01.16.
 */
public class ObservableBoolean extends BaseObservable {

    private boolean value;

    public ObservableBoolean(boolean value) {
        this.value = value;
    }

    public ObservableBoolean() { }

    public boolean get() {
        return value;
    }

    public void set(boolean value) {
        if (this.value != value) {
            this.value = value;
            notifyChange();
        }
    }


    @BindingConversion
    public static boolean convertToBoolean(
            ObservableBoolean observable) {
        return observable.get();
    }
}
