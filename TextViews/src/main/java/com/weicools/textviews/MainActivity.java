package com.weicools.textviews;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
  public static final int CODE_X = 101;

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    TextView textView = findViewById(R.id.title1);
    textView.setTypeface(ResourcesCompat.getFont(this, R.font.nasalization_rg));

    textView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick (View v) {
        //"android.settings.USAGE_ACCESS_SETTINGS"
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        startActivityForResult(intent, CODE_X);

        showToast(Toast.LENGTH_LONG);
      }
    });
  }

  public void showToast (int duration) {
    //Presentation presentation = new Presentation();
    View view = getLayoutInflater().inflate(R.layout.toast_layout, null);
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick (View v) {
        Toast.makeText(v.getContext(), "YYY"+(v.getContext() instanceof MainActivity), Toast.LENGTH_SHORT).show();
      }
    });
    Toast mToast = Toast.makeText(this, "", duration);
    //这里可以指定显示位置
    mToast.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL, 0, 0);

    mToast.setView(view);

    try {
      Object mTN;
      mTN = getField(mToast, "mTN");
      if (mTN != null) {
        Object mParams = getField(mTN, "mParams");
        if (mParams instanceof WindowManager.LayoutParams) {
          WindowManager.LayoutParams params = (WindowManager.LayoutParams) mParams;
          //显示与隐藏动画
          params.windowAnimations = R.style.ClickToast;
          //Toast可点击
          params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

          //设置view group宽高
          //设置Toast宽度为屏幕宽度
          params.width = WindowManager.LayoutParams.MATCH_PARENT;
          //设置高度
          params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    mToast.show();
  }

  private static Object getField (Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
    Field field = object.getClass().getDeclaredField(fieldName);
    field.setAccessible(true);
    return field.get(object);
  }
}
