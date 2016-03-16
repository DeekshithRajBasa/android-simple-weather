package com.stfalcon.sampleweather.binding;

import android.view.animation.Animation;

/**
 * Created by troy379 on 26.02.16.
 */
public class AnimationListener implements Animation.AnimationListener {

    private AnimationStateListener stateListener;

    public AnimationListener(AnimationStateListener stateListener) {
        this.stateListener = stateListener;
    }

    @Override
    public void onAnimationStart(Animation animation) {
        callListener(animation, State.START);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        callListener(animation, State.END);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        callListener(animation, State.REPEAT);
    }

    private void callListener(Animation animation, State state) {
        if (stateListener != null)
            stateListener.onAnimationStateChanged(animation, state);
    }

    public void setStateListener(AnimationStateListener stateListener) {
        this.stateListener = stateListener;
    }

    public interface AnimationStateListener {
        void onAnimationStateChanged(Animation animation, State state);
    }

    public enum State {
        START, END, REPEAT
    }
}
