package com.weicools.custom.scrollview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
  public static final String TAG = "ScrollTest_Main";
  private float lastY;

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //final SxxView sxxView = findViewById(R.id.sxx_view);
    final AdvancedPageLayout ll = findViewById(R.id.advanced_page_layout);
    ll.addListener(new AdvancedPageLayout.PanelSlideListener() {
      @Override
      public void onPanelCollapsed () {
        Log.e(TAG, "onPanelCollapsed: ");
      }

      @Override
      public void onPanelExpanded () {
        Log.e(TAG, "onPanelExpanded: ");
      }

      @Override
      public void onPanelSliding (float slideProgress) {
        Log.e(TAG, "onPanelSliding: " + slideProgress);
      }
    });

    findViewById(R.id.btn_scroll).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick (View v) {
        ll.expendedWithoutAnim();
        startActivity(new Intent(MainActivity.this, TestLayoutActivity.class));
      }
    });
  }

  @Override
  protected void onDestroy () {
    super.onDestroy();
    Log.e(TAG, "onDestroy: ");
  }
}
