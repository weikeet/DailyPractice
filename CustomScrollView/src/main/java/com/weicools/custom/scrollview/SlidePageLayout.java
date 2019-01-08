package com.weicools.custom.scrollview;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Weicools Create on 2019.01.04
 *
 * desc:
 */
public class SlidePageLayout extends FrameLayout {
  public interface PageSlideListener {
    void onPageExpanded ();

    void onPageCollapsed ();

    void onPageSliding (float slideProgress);
  }

  private class DragHelperCallback extends ViewDragHelper.Callback {
    @Override
    public boolean tryCaptureView (@NonNull View child, int pointerId) {
      return child == slidePageView;
    }

    @Override
    public void onViewDragStateChanged (int state) {
      if (dragHelper == null) {
        return;
      }

      if (dragHelper.getViewDragState() == ViewDragHelper.STATE_IDLE) {
        slidedProgress = calcSlidedProgress(slidePageView.getTop());
        if (slidedProgress == 1) {
          if (pageSlideState != EXPANDED) {
            pageSlideState = EXPANDED;
            if (slideListener != null) {
              slideListener.onPageExpanded();
            }
          }
        } else if (slidedProgress == 0) {
          if (pageSlideState != COLLAPSED) {
            pageSlideState = COLLAPSED;
            if (slideListener != null) {
              slideListener.onPageCollapsed();
            }
          }
        }
      }
    }

    @Override
    public void onViewPositionChanged (@NonNull View changedView, int left, int top, int dx, int dy) {
      isSlidingUp = dy < 0;
      pageSlideState = DRAGGING;
      slidedProgress = calcSlidedProgress(top);
      if (slideListener != null) {
        slideListener.onPageSliding(slidedProgress);
      }
    }

    @Override
    public void onViewReleased (@NonNull View releasedChild, float xvel, float yvel) {
      int target;
      if (isSlidingUp) {
        target = slidedProgress >= 0.0f ? 0 : pageSlidingRange;
      } else {
        target = slidedProgress >= 0.7f ? 0 : pageSlidingRange;
      }

      if (dragHelper != null) {
        dragHelper.settleCapturedViewAt(releasedChild.getLeft() + getPaddingLeft(), target);
        invalidate();
      }
    }

    @Override
    public int getViewVerticalDragRange (@NonNull View child) {
      return slidePageView == null ? 0 : pageSlidingRange;
    }

    @Override
    public int clampViewPositionVertical (@NonNull View child, int top, int dy) {
      return Math.min(Math.max(top, 0), pageSlidingRange);
    }
  }

  private static final int DEFAULT_MIN_FLING_VELOCITY = 400;
  public static final int EXPANDED = 0, COLLAPSED = 1, DRAGGING = 2;

  @Retention(RetentionPolicy.SOURCE)
  @IntDef({ EXPANDED, COLLAPSED, DRAGGING })
  public @interface SlideState {}

  @SlidePageLayout.SlideState private int pageSlideState = COLLAPSED;
  private int pageExpandedHeight, pageSlidingRange;

  private boolean isSlidingUp;
  private boolean isFirstLayout = true;
  private boolean isSlidingEnabled = true;

  private float slidedProgress;
  private PageSlideListener slideListener;
  private ViewDragHelper dragHelper;
  private View slidePageView;

  public SlidePageLayout (@NonNull Context context) {
    super(context, null);
  }

  public SlidePageLayout (@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs, 0);
  }

  public SlidePageLayout (@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    int pageCollapsedHeight = context.getResources().getDimensionPixelSize(R.dimen.advanced_page_top_layout_height);
    pageExpandedHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    pageSlidingRange = pageExpandedHeight - pageCollapsedHeight;

    dragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());
    dragHelper.setMinVelocity(DEFAULT_MIN_FLING_VELOCITY * getResources().getDisplayMetrics().density);
  }

  @Override
  protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    slidePageView = getChildAt(1);
  }

  @Override
  protected void onLayout (boolean changed, int left, int top, int right, int bottom) {
    int childCount = getChildCount();
    for (int i = 0; i < childCount; i++) {
      if (i == 1) {
        View child = getChildAt(1);
        int childLeft = getPaddingLeft();
        int childRight = childLeft + child.getMeasuredWidth();

        int pageTop = pageSlideState == COLLAPSED ? pageSlidingRange : 0;
        int childTop = pageTop + getPaddingTop();
        int childBottom = childTop + pageExpandedHeight;
        child.layout(childLeft, childTop, childRight, childBottom);
      } else {
        super.onLayout(changed, left, top, right, bottom);
      }
    }

    isFirstLayout = false;
  }

  @Override
  protected void onAttachedToWindow () {
    super.onAttachedToWindow();
    isFirstLayout = true;
  }

  @Override
  protected void onSizeChanged (int newWidth, int newHeight, int oldWidth, int oldHeight) {
    super.onSizeChanged(newWidth, newHeight, oldWidth, oldHeight);
    if (newHeight != oldHeight) {
      isFirstLayout = true;
    }
  }

  @Override
  public boolean onInterceptTouchEvent (MotionEvent ev) {
    if (!isEnabled() || isSlidingDisabled()) {
      dragHelper.cancel();
      return super.onInterceptTouchEvent(ev);
    }

    int action = ev.getActionMasked();
    if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
      dragHelper.cancel();
      return false;
    }

    return dragHelper.shouldInterceptTouchEvent(ev);
  }

  @Override
  public boolean onTouchEvent (MotionEvent ev) {
    if (isSlidingDisabled()) {
      return super.onTouchEvent(ev);
    }
    if (ev.getActionMasked() == MotionEvent.ACTION_UP) {
      performClick();
    }

    dragHelper.processTouchEvent(ev);
    return true;
  }

  @Override
  public boolean performClick () {
    return super.performClick();
  }

  @Override
  public void computeScroll () {
    if (dragHelper != null && dragHelper.continueSettling(true)) {
      if (isSlidingDisabled()) {
        dragHelper.abort();
        return;
      }
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }

  private float calcSlidedProgress (int topPosition) {
    return (float) (pageSlidingRange - topPosition) / pageSlidingRange;
  }

  private boolean smoothSlidePage (float slideProgress) {
    if (isSlidingDisabled()) {
      return false;
    }

    int slidePageTop = (int) (pageSlidingRange * (1 - slideProgress));
    boolean smoothSlideResult = dragHelper.smoothSlideViewTo(slidePageView, slidePageView.getLeft(), slidePageTop);
    if (smoothSlideResult) {
      ViewCompat.postInvalidateOnAnimation(this);
      return true;
    }
    return false;
  }

  public boolean expandPage () {
    if (slidePageView == null) {
      return false;
    }

    if (isFirstLayout) {
      pageSlideState = EXPANDED;
      return true;
    } else {
      return pageSlideState == EXPANDED || smoothSlidePage(1.0f);
    }
  }

  public boolean collapsePage () {
    if (slidePageView == null) {
      return false;
    }

    if (isFirstLayout) {
      pageSlideState = COLLAPSED;
      return true;
    } else {
      return pageSlideState == COLLAPSED || smoothSlidePage(0.0f);
    }
  }

  public void setPageSlideListener (PageSlideListener listener) {
    slideListener = listener;
  }

  public void setPageSlideState (int pageSlideState) {
    this.pageSlideState = pageSlideState;
  }

  public int getPageSlideState () {
    return pageSlideState;
  }

  public void setSlidingEnabled (boolean enabled) {
    isSlidingEnabled = enabled;
  }

  public boolean isSlidingDisabled () {
    return !isSlidingEnabled || slidePageView == null;
  }
}
