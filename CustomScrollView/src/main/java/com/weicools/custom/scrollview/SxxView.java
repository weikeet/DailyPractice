package com.weicools.custom.scrollview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author Weicools Create on 2019.01.02
 *
 * desc:
 */
public class SxxView extends View {
  private float cx, cy;
  private Paint mPaint;

  public SxxView (Context context) {
    super(context);
    init();
  }

  public SxxView (Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public SxxView (Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init () {
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setColor(Color.BLUE);
    mPaint.setStrokeCap(Paint.Cap.ROUND);
  }

  @Override
  protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    cx = getMeasuredWidth() / 2.0f;
    cy = getMeasuredHeight() / 2.0f;
  }

  @Override
  protected void onDraw (Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawCircle(cx, cy, 200, mPaint);
  }

  @Override
  public boolean onTouchEvent (MotionEvent event) {
    cx = event.getX();
    cy = event.getY();
    invalidate();
    return true;
  }
}
