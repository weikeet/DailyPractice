package com.weiwei.practice.widget.rvsticky;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.weiwei.practice.R;

public class RvStickyMainActivity extends AppCompatActivity {
  private RecyclerView mFeedList;
  private RelativeLayout mSuspensionBar;
  private TextView mSuspensionTv;
  private ImageView mSuspensionIv;

  private int mSuspensionHeight;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.rvsticky_activity_main);

    mSuspensionBar = (RelativeLayout) findViewById(R.id.suspension_bar);
    mSuspensionTv = (TextView) findViewById(R.id.tv_nickname);
    mSuspensionIv = (ImageView) findViewById(R.id.iv_avatar);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.inflateMenu(R.menu.rvsticky_menu_main);
    toolbar.setOnMenuItemClickListener(item -> {
      if (item.getItemId() == R.id.item_jump) {
        Intent intent = new Intent(RvStickyMainActivity.this, RvStickyMultiActivity.class);
        startActivity(intent);
      }
      return false;
    });

    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    final RvStickyFeedAdapter adapter = new RvStickyFeedAdapter();

    mFeedList = (RecyclerView) findViewById(R.id.feed_list);
    mFeedList.setLayoutManager(linearLayoutManager);
    mFeedList.setAdapter(adapter);
    mFeedList.setHasFixedSize(true);

    mFeedList.addOnScrollListener(new RecyclerView.OnScrollListener() {
      private int mCurrentPosition = 0;

      @Override
      public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        mSuspensionHeight = mSuspensionBar.getHeight();
      }

      @Override
      public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        View view = linearLayoutManager.findViewByPosition(mCurrentPosition + 1);
        if (view != null) {
          if (view.getTop() <= mSuspensionHeight) {
            mSuspensionBar.setY(-(mSuspensionHeight - view.getTop()));
          } else {
            mSuspensionBar.setY(0);
          }
        }

        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        if (mCurrentPosition != firstVisibleItemPosition) {
          mCurrentPosition = firstVisibleItemPosition;

          updateSuspensionBar(mCurrentPosition);
          mSuspensionBar.setY(0);
        }
      }
    });

    updateSuspensionBar(0);
  }

  private void updateSuspensionBar(int mCurrentPosition) {
    Log.d("HHHH", "updateSuspensionBar: " + mCurrentPosition);
    Glide.with(this)
        .load(getAvatarResId(mCurrentPosition))
        .centerInside()
        .into(mSuspensionIv);

    mSuspensionTv.setText("Taeyeon " + mCurrentPosition);
  }

  private int getAvatarResId(int position) {
    switch (position % 4) {
      case 0:
        return R.drawable.rvsticky_avatar1;
      case 1:
        return R.drawable.rvsticky_avatar2;
      case 2:
        return R.drawable.rvsticky_avatar3;
      case 3:
        return R.drawable.rvsticky_avatar4;
    }
    return 0;
  }
}
