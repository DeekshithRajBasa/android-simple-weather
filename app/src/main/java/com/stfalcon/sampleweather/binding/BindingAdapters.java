package com.stfalcon.sampleweather.binding;

import android.databinding.BindingAdapter;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.stfalcon.sampleweather.R;
import com.stfalcon.sampleweather.binding.fields.ObservableBoolean;
import com.stfalcon.sampleweather.binding.fields.ObservableString;
import com.stfalcon.sampleweather.ui.custom.views.EditTextDrawableClickable;

/**
 * Created by alex on 11.01.16.
 */
public class BindingAdapters {
    private BindingAdapters() {
        throw new AssertionError();
    }

    @BindingAdapter("app:activity")
    public static void setSupportActionBar(Toolbar toolbar, AppCompatActivity activity) {
        activity.setSupportActionBar(toolbar);
    }

    @BindingAdapter("android:text")
    public static void bindEditText(EditText view,
                                    final ObservableString observableString) {
        bindEditText(view, observableString, null);
    }

    @BindingAdapter({"android:text", "app:watcher"})
    public static void bindEditText(EditText view,
                                    final ObservableString observableString, TextChangeListener textChangeListener) {
        Pair<ObservableString, TextChangeListener> pair = (Pair) view.getTag(R.id.bound_observable);

        if (pair == null || pair.first != observableString) {
            if (pair != null) view.removeTextChangedListener(pair.second);

            if (textChangeListener == null) {
                textChangeListener = new TextChangeListener(
                        (s, start, before, count) -> observableString.set(s.toString()));
            } else {
                textChangeListener.setNecessaryOnTextChangedListener(
                        (s, start, before, count) -> observableString.set(s.toString()));
            }

            view.setTag(R.id.bound_observable, new Pair<>(observableString, textChangeListener));
            view.addTextChangedListener(textChangeListener);
        }
        String newValue = observableString.get();
        if (!view.getText().toString().equals(newValue))
            view.setText(newValue);
    }

    @BindingAdapter("app:checked")
    public static void bindChecked(CompoundButton view, final ObservableBoolean value) {
        if (view.getTag(R.id.bound_observable) == null) {
            view.setTag(R.id.bound_observable, true);
            view.setOnCheckedChangeListener((buttonView, isChecked) -> value.set(isChecked));
        }
        boolean newValue = value.get();
        if (view.isChecked() != newValue) {
            view.setChecked(newValue);
        }
    }

    @BindingAdapter({"app:autoOpenLinks"})
    public static void bindAutoOpenLink(TextView view, boolean enable) {
        if (enable) {
            view.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    @BindingAdapter({"app:onDrawableClick"})
    public static void bindOnDrawableClick(EditTextDrawableClickable view, final Runnable runnable) {
        view.setDrawableClickListener(runnable::run);
    }

    @BindingAdapter({"app:onClick"})
    public static void bindOnClick(View view, final Runnable runnable) {
        view.setOnClickListener(v -> runnable.run());
    }

    @BindingAdapter("app:gone")
    public static void setIsGone(View view, boolean hide) {
        view.setVisibility(hide ? View.GONE : View.VISIBLE);
    }

    @BindingAdapter({"app:goneAnim"})
    public static void animateIsGone(View view, boolean hide) {
        int visibility = hide ? View.GONE : View.VISIBLE;

        if (view.getVisibility() != visibility) {
            Animation animation;

            if (hide) animation = new AlphaAnimation(1, 0);
            else animation = new AlphaAnimation(0, 1);

            animation.setDuration(1000);

            animation.setAnimationListener(new AnimationListener((animation1, state) -> {
                switch (state) {
                    case START:
                        view.setVisibility(hide ? View.VISIBLE : View.GONE);
                        break;
                    case END:
                        view.setVisibility(visibility);
                        break;
                }
            }));

            view.startAnimation(animation);
        }
    }

    @BindingAdapter("app:invisible")
    public static void setIsInvisible(View view, boolean hide) {
        view.setVisibility(hide ? View.INVISIBLE : View.VISIBLE);
    }

    @BindingAdapter("onNavigationClick")
    public static void bindOnNavigationClick(Toolbar view, final Runnable runnable) {
        view.setNavigationOnClickListener(v -> runnable.run());
    }

    @BindingAdapter("app:adapter")
    public static void bindAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("app:layoutManager")
    public static void bindLayoutManager(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

   /* @BindingAdapter("android:src")
    public static void loadImage(ImageView view, String url) {
        AppUtilities.loadImage(view, url);
    }

    @BindingAdapter({"android:src", "app:holder"})
    public static void loadImage(ImageView view, String url, @DrawableRes Drawable holder) {
        AppUtilities.loadImage(view, url, holder);
    }

    @BindingAdapter({"android:src", "app:onImageLoaded"})
    public static void loadImageWithListener(ImageView view, String url, Runnable runnable) {
        Picasso.with(view.getContext()).load(url).into(view, new Callback() {
            @Override
            public void onSuccess() {
                runnable.run();
            }

            @Override
            public void onError() {

            }
        });
    }

    @BindingAdapter({"android:src", "app:sizeX", "app:sizeY"})
    public static void loadImage(ImageView view, String url, int sizeX, int sizeY) {
        AppUtilities.loadImage(view, url, sizeX, sizeY);
    }

    @BindingAdapter("app:status")
    public static void bindIndicatorStatus(IndicatorView view, int status) {
        switch (status) {
            case Message.STATUS_PENDING:
                view.setStatus(IndicatorView.State.PENDING);
                break;
            case Message.STATUS_DELIVERED:
                view.setStatus(IndicatorView.State.DELIVERED);
                break;
            case Message.STATUS_READ:
                view.setStatus(IndicatorView.State.READ);
                break;
            case Message.STATUS_SENT:
                view.setStatus(IndicatorView.State.SENT);
                break;
        }
    }*/
}