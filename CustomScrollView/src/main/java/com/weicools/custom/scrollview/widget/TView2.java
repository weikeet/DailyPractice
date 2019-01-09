package com.weicools.custom.scrollview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * @author Weicools Create on 2019.01.08
 *
 * desc:
 */
public class TView2 extends AppCompatTextView {
  public TView2 (Context context) {
    super(context);
  }

  public TView2 (Context context,  @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public TView2 (Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    Log.e("ZZW", "TV2 onMeasure: ");
  }

  @Override
  protected void onLayout (boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    Log.e("ZZW", "TV2 onLayout: ");
  }

  @Override
  protected void onDraw (Canvas canvas) {
    super.onDraw(canvas);
    Log.e("ZZW", "TV2 onDraw: ");
  }
}
