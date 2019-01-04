package com.weicools.custom.scrollview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Weicools Create on 2019.01.02
 *
 * desc:
 */
public class ScLayout extends ViewGroup {
  public ScLayout (Context context) {
    super(context);
  }

  public ScLayout (Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public ScLayout (Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    Log.e("MLD", "onMeasure: ++++");

    final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
    final int heightSize = MeasureSpec.getSize(heightMeasureSpec);

    final int childCount = getChildCount();
    int layoutHeight = heightSize - getPaddingTop() - getPaddingBottom();

    // First pass. Measure based on child LayoutParams width/height.
    for (int i = 0; i < childCount; i++) {
      View child = getChildAt(i);
      LayoutParams lp = child.getLayoutParams();
      // We always measure the sliding panel in order to know it's height (needed for show panel)
      if (child.getVisibility() == GONE && i == 0) {
        continue;
      }

      int childWidthSpec;
      if (lp.width == LayoutParams.WRAP_CONTENT) {
        childWidthSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.AT_MOST);
      } else if (lp.width == LayoutParams.MATCH_PARENT) {
        childWidthSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY);
      } else {
        childWidthSpec = MeasureSpec.makeMeasureSpec(lp.width, MeasureSpec.EXACTLY);
      }

      int childHeightSpec;
      if (lp.height == LayoutParams.WRAP_CONTENT) {
        childHeightSpec = MeasureSpec.makeMeasureSpec(layoutHeight, MeasureSpec.AT_MOST);
      } else if (lp.height == LayoutParams.MATCH_PARENT) {
        childHeightSpec = MeasureSpec.makeMeasureSpec(layoutHeight, MeasureSpec.EXACTLY);
      } else {
        childHeightSpec = MeasureSpec.makeMeasureSpec(lp.height, MeasureSpec.EXACTLY);
      }

      child.measure(childWidthSpec, childHeightSpec);
    }

    setMeasuredDimension(widthSize, heightSize);
  }

  @Override
  protected void onLayout (boolean changed, int l, int t, int r, int b) {
    Log.e("MLD", "onLayout: ++++");

    final int paddingLeft = getPaddingLeft();
    final int paddingTop = getPaddingTop();
    int childCount = getChildCount();
    for (int i = 0; i < childCount; i++) {
      View child = getChildAt(i);
      int childTop = paddingTop;
      int childBottom = childTop + child.getMeasuredHeight();
      int childLeft = paddingLeft;
      int childRight = childLeft + child.getMeasuredWidth();

      if (i == 1 && child instanceof AdvancedPageLayout) {
        AdvancedPageLayout pageLayout = (AdvancedPageLayout) child;
        childTop = paddingTop + pageLayout.getRange();
        childBottom = childTop + pageLayout.getPanelExpendedHeight();
      }

      child.layout(childLeft, childTop, childRight, childBottom);
    }
  }
}
