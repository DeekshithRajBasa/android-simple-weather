package com.stfalcon.sampleweather.utils;

import java.text.DecimalFormat;

/**
 * Created by artem on 17.03.16.
 */
public class NumberUtilities {
    private NumberUtilities() { throw new AssertionError(); }

    @SuppressWarnings("all")
    public static String formatNumber(float number) {
        return new DecimalFormat("##.#").format(number);
    }
}
