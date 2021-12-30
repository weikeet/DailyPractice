package com.weiwei.practice.widget;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

/**
 * @author weicools
 */
public class LinearGradientDrawView extends View {

  private final static int[] START_COLORS = new int[] { 0xFF29C645, 0xFFFFAF00, 0xFFF4511E };
  private final static int[] END_COLORS = new int[] { 0xFF00B076, 0xFFF57C00, 0xFFE53936 };

  private int[] colors = new int[] { START_COLORS[0], END_COLORS[0] };

  private Paint paint = new Paint();

  private int width, height;

  private ValueAnimator valueAnimator;
  private ArgbEvaluator argbEvaluator = new ArgbEvaluator();

  //<editor-fold desc="Constructor">
  public LinearGradientDrawView(Context context) {
    super(context);
  }

  public LinearGradientDrawView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public LinearGradientDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }
  //</editor-fold>

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);

    this.width = w;
    this.height = h;
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
    this.colors[0] = startColor;
    this.colors[1] = endColor;
    invalidate();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    paint.setShader(getCurrentGradient());
    canvas.drawRect(0, 0, width, height, paint);
  }

  private LinearGradient getCurrentGradient() {//此类不支持改参数，只能new
    return new LinearGradient(0, 0, 0, height, colors, null, Shader.TileMode.CLAMP);
  }
}