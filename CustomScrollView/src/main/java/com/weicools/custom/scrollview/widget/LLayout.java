package com.weicools.custom.scrollview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * @author Weicools Create on 2019.01.08
 *
 * desc:
 */
public class LLayout extends LinearLayout {
  public LLayout (Context context) {
    super(context);
  }

  public LLayout (Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public LLayout (Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    Log.e("ZZW", "LL onMeasure: ");
  }

  @Override
  protected void onLayout (boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    Log.e("ZZW", "LL onLayout: ");
  }

  @Override
  protected void onDraw (Canvas canvas) {
    super.onDraw(canvas);
    Log.e("ZZW", "LL onDraw: ");
  }
}
