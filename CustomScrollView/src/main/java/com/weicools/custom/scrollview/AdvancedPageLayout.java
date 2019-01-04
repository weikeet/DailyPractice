package com.weicools.custom.scrollview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
public class AdvancedPageLayout extends LinearLayout {
  public interface PanelSlideListener {
    void onPanelCollapsed ();

    void onPanelExpanded ();

    void onPanelSliding (float slideProgress);
  }

  public static final String TAG = "ScrollTest_Layout";

  private int lastY, lastTop;
  private int panelExpendedHeight, panelCollapsedHeight, range;
  private boolean isStartAnim;

  private PanelSlideListener mListener;

  public AdvancedPageLayout (Context context) {
    super(context);
    init(context);
  }

  public AdvancedPageLayout (Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public AdvancedPageLayout (Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init (Context context) {
    panelCollapsedHeight = context.getResources().getDimensionPixelSize(R.dimen.advanced_page_top_layout_height);
    panelExpendedHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    range = panelExpendedHeight - panelCollapsedHeight;
  }

  public void addListener (PanelSlideListener listener) {
    mListener = listener;
  }

  @Override
  public boolean onTouchEvent (MotionEvent event) {
    if (isStartAnim) {
      return false;
    }
    int y = (int) event.getY();
    int currTop = getTop();
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        lastY = y;
        lastTop = currTop;
        break;
      case MotionEvent.ACTION_MOVE:
        int offsetY = y - lastY;
        boolean shouldSlideLayout =
            (currTop < range && currTop > 0) || (currTop == 0 && offsetY > 0) || (currTop == range && offsetY < 0);
        if (shouldSlideLayout) {
          if (currTop + offsetY > range) {
            offsetY = range - currTop;
          } else if (currTop + offsetY < 0) {
            offsetY = 0 - currTop;
          }
          layout(getLeft(), currTop + offsetY, getRight(), currTop + panelExpendedHeight + offsetY);
          mListener.onPanelSliding(1 - ((float) currTop / range));
        }
        //offsetTopAndBottom(offsetY);
        break;
      case MotionEvent.ACTION_UP:
        int currLeft = getLeft();
        int currRight = getRight();
        int offset = Math.abs(currTop - lastTop);
        if (currTop < lastTop) {
          if (offset < 0.3 * range) {
            collapsed(offset, currLeft, currTop, currRight);
          } else {
            expended(range - offset, currLeft, currTop, currRight);
          }
        } else {
          if (offset < 0.3 * range) {
            expended(offset, currLeft, currTop, currRight);
          } else {
            collapsed(range - offset, currLeft, currTop, currRight);
          }
        }
        break;
      default:
        break;
    }
    return true;
  }

  private void collapsed (final int d, final int currLeft, final int currTop, final int currRight) {
    Log.e(TAG, "collapsed: start--" + d);
    if (d == 0) {
      mListener.onPanelSliding(0);
      mListener.onPanelCollapsed();
      return;
    }
    isStartAnim = true;
    ValueAnimator animator = ValueAnimator.ofInt(0, d);
    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate (ValueAnimator animation) {
        int val = (int) animation.getAnimatedValue();
        int top = currTop + val;
        mListener.onPanelSliding(1 - ((float) top / range));
        layout(currLeft, top, currRight, top + panelExpendedHeight);
      }
    });
    animator.addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd (Animator animation) {
        isStartAnim = false;
        mListener.onPanelCollapsed();
      }
    });
    animator.setDuration(500);
    animator.start();
  }

  private void expended (final int d, final int currLeft, final int currTop, final int currRight) {
    Log.e(TAG, "expended: start--" + d);
    if (d == 0) {
      mListener.onPanelSliding(1);
      mListener.onPanelCollapsed();
      return;
    }
    isStartAnim = true;
    ValueAnimator animator = ValueAnimator.ofInt(0, d);
    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate (ValueAnimator animation) {
        int val = (int) animation.getAnimatedValue();
        int top = currTop - val;
        mListener.onPanelSliding(1 - ((float) top / range));
        layout(currLeft, top, currRight, top + panelExpendedHeight);
      }
    });

    animator.addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd (Animator animation) {
        isStartAnim = false;
        mListener.onPanelExpanded();
      }
    });
    animator.setDuration(500);
    animator.start();
  }

  public void expendedWithoutAnim () {
    layout(getLeft(), 0, getRight(), panelExpendedHeight);
  }

  public void expended () {
    expended(range, getLeft(), getTop(), getRight());
  }

  public void collapsed () {
    collapsed(range, getLeft(), getTop(), getRight());
  }

  public int getRange () {
    return range;
  }

  public int getPanelExpendedHeight () {
    return panelExpendedHeight;
  }
}
