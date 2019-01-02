package com.weicools.custom.scrollview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * @author Weicools Create on 2019.01.02
 *
 * desc:
 */
public class AdvancedLayout extends LinearLayout {
  public static final String TAG = "AdvancedLayout";
  private int lastY, lastTop;

  private int mPanelExpendedHeight;
  private int mPanelCollapsedHeight;
  private int range;

  public AdvancedLayout (Context context) {
    super(context);
    init(context);
  }

  public AdvancedLayout (Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public AdvancedLayout (Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init (Context context) {
    mPanelCollapsedHeight = context.getResources().getDimensionPixelSize(R.dimen.advanced_page_top_layout_height);
    mPanelExpendedHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    range = mPanelExpendedHeight - mPanelCollapsedHeight;
  }

  @Override
  protected void onLayout (boolean changed, int l, int t, int r, int b) {
    super.onLayout(changed, l, t, r, b);
  }

  @Override
  public boolean onTouchEvent (MotionEvent event) {
    int y = (int) event.getY();
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        lastY = y;
        lastTop = getTop();
        Log.e(TAG, "onTouchEvent: last--" + lastY);
        break;
      case MotionEvent.ACTION_MOVE:
        int offsetY = y - lastY;
        Log.e(TAG, "onTouchEvent: offset--" + offsetY);
        layout(getLeft(), getTop() + offsetY, getRight(), getBottom() + offsetY);
        //offsetTopAndBottom(offsetY);
        break;
      case MotionEvent.ACTION_UP:
        int currTop = getTop();
        int currBottom = getBottom();
        int currLeft = getLeft();
        int currRight = getRight();
        int offset = Math.abs(currTop - lastTop);
        if (currTop < lastTop) {
          if (offset < range / 2) {
            collapsed(offset, currLeft, currTop, currRight, currBottom);
          } else {
            expended(range - offset, currLeft, currTop, currRight, currBottom);
          }
        } else {
          if (offset < range/2) {
            expended(offset, currLeft, currTop, currRight, currBottom);
          } else {
            collapsed(range - offset, currLeft, currTop, currRight, currBottom);
          }
        }
        break;
      default:
        break;
    }
    return true;
  }

  private void collapsed (int d, final int currLeft, final int currTop, final int currRight, final int currBottom) {
    ValueAnimator animator = ValueAnimator.ofInt(0, d);
    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate (ValueAnimator animation) {
        int val = (int) animation.getAnimatedValue();
        layout(currLeft, currTop + val, currRight, currBottom + val);
      }
    });
    animator.setDuration(500);
    animator.start();
  }

  private void expended (int d, final int currLeft, final int currTop, final int currRight, final int currBottom) {
    ValueAnimator animator = ValueAnimator.ofInt(0, d);
    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate (ValueAnimator animation) {
        int val = (int) animation.getAnimatedValue();
        layout(currLeft, currTop - val, currRight, currBottom - val);
      }
    });
    animator.setDuration(500);
    animator.start();
  }
}
