package com.stfalcon.sampleweather.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by artem on 17.03.16.
 */
public final class DateUtilities {
    private DateUtilities() { throw new AssertionError(); }

    @SuppressWarnings("all")
    public static String dateFromUnix(long unixStamp) {
        return new SimpleDateFormat("dd.MM.yyyy").format(new Date(unixStamp*1000));
    }
}
