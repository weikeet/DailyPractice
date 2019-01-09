package com.weicools.custom.scrollview;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.weicools.custom.scrollview.widget.LLayout;
import com.weicools.custom.scrollview.widget.TView1;
import com.weicools.custom.scrollview.widget.TView2;

public class TestLayoutActivity extends AppCompatActivity {
  private LLayout ll;
  private TView1 tv1;
  private TView2 tv2;

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_layout);

    ll = findViewById(R.id.ll);
    tv1 = findViewById(R.id.tv1);
    tv2 = findViewById(R.id.tv2);

    findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick (View v) {
        tv1.setTextColor(ContextCompat.getColor(TestLayoutActivity.this, R.color.colorAccent));
        tv2.setTextColor(ContextCompat.getColor(TestLayoutActivity.this, R.color.colorPrimary));
      }
    });
  }
}
