package com.weicools.custom.scrollview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

/**
 * @author Weicools Create on 2019.01.02
 *
 * desc:
 */
public class ScLayout extends FrameLayout {
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
  protected void onLayout (boolean changed, int l, int t, int r, int b) {
    Log.e("MLD", "onLayout: ++++");
    int childCount = getChildCount();
    for (int i = 0; i < childCount; i++) {
      View child = getChildAt(i);
      if (i == 1 && child instanceof AdvancedPageLayout) {
        AdvancedPageLayout pageLayout = (AdvancedPageLayout) child;
        int childTop = getPaddingLeft() + pageLayout.getRange();
        int childBottom = childTop + pageLayout.getPanelExpendedHeight();
        int childLeft = getPaddingLeft();
        int childRight = childLeft + child.getMeasuredWidth();
        child.layout(childLeft, childTop, childRight, childBottom);
      } else {
        super.onLayout(changed, l, t, r, b);
      }
    }
  }
}
