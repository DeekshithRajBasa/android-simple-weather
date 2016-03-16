package com.stfalcon.sampleweather.ui.custom.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * Created by Anton Bevza on 18.02.16.
 */
public class EditTextDrawableClickable extends EditText {
    private final int DRAWABLE_LEFT = 0;
    private final int DRAWABLE_TOP = 1;
    private final int DRAWABLE_RIGHT = 2;
    private final int DRAWABLE_BOTTOM = 3;

    private DrawableClickListener drawableClickListener;

    public EditTextDrawableClickable(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (getRight() - getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    if (drawableClickListener != null) {
                        drawableClickListener.onClickDrawable();
                    }
                    return true;
                }
            }
            return false;
        });
    }

    public DrawableClickListener getDrawableClickListener() {
        return drawableClickListener;
    }

    public void setDrawableClickListener(DrawableClickListener drawableClickListener) {
        this.drawableClickListener = drawableClickListener;
    }

    public interface DrawableClickListener {
        void onClickDrawable();
    }
}
