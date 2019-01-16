package io.weicools.sp.analyze;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    SharedPreferences sp = getSharedPreferences("settings", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sp.edit();
    editor.putString("hello", "world");
    editor.putInt("goal", 3000);
    editor.apply();
  }
}
