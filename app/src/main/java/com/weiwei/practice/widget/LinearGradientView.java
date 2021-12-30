package com.weiwei.practice.widget;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

/**
 * @author weicools
 */
public class LinearGradientView extends View {
  public interface GradientListener {
    void onColorChanged(@ColorInt int startColor, @ColorInt int endColor);
  }

  private final static int[] START_COLORS = new int[] { 0xFF29C645, 0xFFFFAF00, 0xFFF4511E };
  private final static int[] END_COLORS = new int[] { 0xFF00B076, 0xFFF57C00, 0xFFE53936 };

  private int[] gradientColors = new int[] { START_COLORS[0], END_COLORS[0] };

  private GradientDrawable gradientDrawable;

  @Nullable
  private GradientListener gradientListener;

  private ValueAnimator valueAnimator;
  private ArgbEvaluator argbEvaluator = new ArgbEvaluator();

  //<editor-fold desc="Constructor">
  public LinearGradientView(Context context) {
    super(context);
    init();
  }

  public LinearGradientView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public LinearGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }
  //</editor-fold>

  private void init() {
    gradientDrawable = new GradientDrawable();
    gradientDrawable.setColors(gradientColors);
    setBackground(gradientDrawable);
  }

  public void setGradientListener(@Nullable GradientListener gradientListener) {
    this.gradientListener = gradientListener;
  }

  public void changeColorSmoothly() {
    if (valueAnimator != null) {
      valueAnimator.cancel();
    }
    valueAnimator = ValueAnimator.ofFloat(0f, 1f);
    valueAnimator.setDuration(500L);
    valueAnimator.setInterpolator(new LinearInterpolator());
    valueAnimator.addUpdateListener(animation -> {
      float fraction = animation.getAnimatedFraction();
      int startColor = (int) argbEvaluator.evaluate(fraction, START_COLORS[0], START_COLORS[1]);
      int endColor = (int) argbEvaluator.evaluate(fraction, END_COLORS[0], END_COLORS[1]);
      updateColor(startColor, endColor);
    });
    valueAnimator.setRepeatCount(4);
    valueAnimator.start();
  }

  private void updateColor(@ColorInt int startColor, @ColorInt int endColor) {
    gradientColors[0] = startColor;
    gradientColors[1] = endColor;
    gradientDrawable.setColors(gradientColors);
    setBackground(gradientDrawable);

    if (gradientListener != null) {
      gradientListener.onColorChanged(startColor, endColor);
    }
  }
}