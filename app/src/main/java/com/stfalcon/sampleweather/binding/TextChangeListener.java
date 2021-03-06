package com.stfalcon.sampleweather.binding;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by troy379 on 22.01.16.
 */
public class TextChangeListener implements TextWatcher {

    private BeforeTextChangedListener beforeTextChangedListener;
    private OnTextChangedListener onTextChangedListener;
    private AfterTextChangedListener afterTextChangedListener;
    private OnTextChangedListener necessaryOnTextChangedListener;

    public TextChangeListener(OnTextChangedListener necessaryOnTextChangedListener) {
        this.necessaryOnTextChangedListener = necessaryOnTextChangedListener;
    }

    public TextChangeListener(BeforeTextChangedListener beforeTextChangedListener,
                              OnTextChangedListener onTextChangedListener,
                              AfterTextChangedListener afterTextChangedListener) {
        this.beforeTextChangedListener = beforeTextChangedListener;
        this.onTextChangedListener = onTextChangedListener;
        this.afterTextChangedListener = afterTextChangedListener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (beforeTextChangedListener != null)
            beforeTextChangedListener.beforeTextChanged(s, start, count, after);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (onTextChangedListener != null)
            onTextChangedListener.onTextChanged(s, start, before, count);
        if (necessaryOnTextChangedListener != null)
            necessaryOnTextChangedListener.onTextChanged(s, start, before, count);
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (afterTextChangedListener != null)
            afterTextChangedListener.afterTextChanged(s);
    }

    public void setNecessaryOnTextChangedListener(OnTextChangedListener necessaryOnTextChangedListener) {
        this.necessaryOnTextChangedListener = necessaryOnTextChangedListener;
    }

    public interface BeforeTextChangedListener {
        void beforeTextChanged(CharSequence s, int start, int count, int after);
    }

    public interface OnTextChangedListener {
        void onTextChanged(CharSequence s, int start, int before, int count);
    }

    public interface AfterTextChangedListener {
        void afterTextChanged(Editable s);
    }
}
