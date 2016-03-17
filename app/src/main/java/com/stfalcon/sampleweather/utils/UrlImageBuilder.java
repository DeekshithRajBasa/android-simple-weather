package com.stfalcon.sampleweather.utils;

import com.stfalcon.sampleweather.BuildConfig;

/**
 * Created by artem on 17.03.16.
 */
public final class UrlImageBuilder {

    private UrlImageBuilder() {
        throw new AssertionError();
    }

    @SuppressWarnings("all")
    public static String build(String tail) {
        return BuildConfig.SAMPLEWEATHER_API_URL + "/img/w/" + tail;
    }
}
