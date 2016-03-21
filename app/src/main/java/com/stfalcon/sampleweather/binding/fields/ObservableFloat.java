package com.stfalcon.sampleweather.binding.fields;

import android.databinding.BaseObservable;
import android.databinding.BindingConversion;

/**
 * Created by artem on 21.03.16.
 */
public class ObservableFloat extends BaseObservable {
    private float value;

    public ObservableFloat(float value) {
        this.value = value;
    }

    public ObservableFloat() {
    }

    public float get() {
        return value;
    }

    public void set(float value) {
        if (this.value != value) {
            this.value = value;
            notifyChange();
        }
    }

    @BindingConversion
    public static float convertToFloat(
            ObservableFloat observableFloat) {
        return observableFloat.get();
    }
}
