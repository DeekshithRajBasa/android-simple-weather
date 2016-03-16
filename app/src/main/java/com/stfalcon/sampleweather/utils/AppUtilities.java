package com.stfalcon.sampleweather.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by troy379 on 19.02.16.
 */
public final class AppUtilities {
    private AppUtilities() {
        throw new AssertionError();
    }

    public static void showToast(Context context, @StringRes int resource, boolean isLong) {
        showToast(context, context.getString(resource), isLong);
    }

    public static void showToast(Context context, String message, boolean isLong) {
        if (message != null && !message.isEmpty()) {
            Toast.makeText(
                    context,
                    message,
                    isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT
            ).show();
        }
    }



    public static void doWithDelay(Activity activity, long delay, Runnable runnable) {
        final Handler handler = new Handler();
        handler.postDelayed(() -> activity.runOnUiThread(runnable), delay);
    }

    public static void vibrate(Context context, long duration) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(duration);
    }
}
