package com.weicools.custom.scrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
  private float lastY;

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //final SxxView sxxView = findViewById(R.id.sxx_view);
    final LinearLayout ll = findViewById(R.id.ll);
  }
}
