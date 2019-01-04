package com.weicools.custom.scrollview;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class TestLayoutActivity extends AppCompatActivity {

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_layout);

    LinearLayout ll = findViewById(R.id.ll_test);
    FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ll.getLayoutParams());
    //lp.
    int mPanelCollapsedHeight = getResources().getDimensionPixelSize(R.dimen.advanced_page_top_layout_height);
    int mPanelExpendedHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    int range = mPanelExpendedHeight - mPanelCollapsedHeight;

    ll.layout(ll.getLeft(), range, ll.getRight(), range + mPanelExpendedHeight);
  }
}
