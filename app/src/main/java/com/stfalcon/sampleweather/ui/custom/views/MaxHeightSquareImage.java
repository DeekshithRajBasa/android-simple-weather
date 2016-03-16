package com.stfalcon.sampleweather.ui.custom.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by troy379 on 25.02.16.
 */
public class MaxHeightSquareImage extends ImageView {

    public MaxHeightSquareImage(Context context) {
        super(context);
    }

    public MaxHeightSquareImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        height = height > width ? width : height;

        setMeasuredDimension(width, height);
    }
}
