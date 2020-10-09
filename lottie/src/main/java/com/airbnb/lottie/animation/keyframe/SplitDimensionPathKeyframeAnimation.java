package com.airbnb.lottie.animation.keyframe;

import android.graphics.PointF;

import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;

import java.util.Collections;

import androidx.annotation.Nullable;

public class SplitDimensionPathKeyframeAnimation extends BaseKeyframeAnimation<PointF, PointF> {
  private final PointF point = new PointF();
  private final BaseKeyframeAnimation<Float, Float> xAnimation;
  private final BaseKeyframeAnimation<Float, Float> yAnimation;

  public SplitDimensionPathKeyframeAnimation(
      BaseKeyframeAnimation<Float, Float> xAnimation,
      BaseKeyframeAnimation<Float, Float> yAnimation) {
    super(Collections.<Keyframe<PointF>>emptyList());

    this.xAnimation = xAnimation;
    this.yAnimation = yAnimation;
    // We need to call an initial setProgress so point gets set with the initial value.
    setProgress(getProgress());
  }

  @Override public void setProgress(float progress) {
    xAnimation.setProgress(progress);
    yAnimation.setProgress(progress);
    point.set(xAnimation.getValue(), yAnimation.getValue());
    for (int i = 0; i < listeners.size(); i++) {
      listeners.get(i).onValueChanged();
    }
  }

    public void setXValueCallback(@Nullable LottieValueCallback<Float> valueCallback) {
        xAnimation.setValueCallback(valueCallback);
    }

    public void setYValueCallback(@Nullable LottieValueCallback<Float> valueCallback) {
        yAnimation.setValueCallback(valueCallback);
    }

    @Override public PointF getValue() {
    return getValue(null, 0);
  }

  @Override PointF getValue(Keyframe<PointF> keyframe, float keyframeProgress) {
    return point;
  }
}
