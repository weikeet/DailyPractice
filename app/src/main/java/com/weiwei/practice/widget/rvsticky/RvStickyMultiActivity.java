package com.weiwei.practice.widget.rvsticky;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.weiwei.practice.R;

public class RvStickyMultiActivity extends AppCompatActivity {
  private RecyclerView mFeedList;
  private RelativeLayout mSuspensionBar;
  private TextView mSuspensionTv;
  private int mCurrentPosition = 0;

  private int mSuspensionHeight;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.rvsticky_activity_multi);

    mSuspensionBar = (RelativeLayout) findViewById(R.id.suspension_bar);
    mSuspensionTv = (TextView) findViewById(R.id.tv_time);

    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    final RvStickyMultiFeedAdapter adapter = new RvStickyMultiFeedAdapter();

    mFeedList = (RecyclerView) findViewById(R.id.feed_list);
    mFeedList.setLayoutManager(linearLayoutManager);
    mFeedList.setAdapter(adapter);
    mFeedList.setHasFixedSize(true);

    mFeedList.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        mSuspensionHeight = mSuspensionBar.getHeight();
      }

      @Override
      public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int nextPosition = mCurrentPosition + 1;
        if (adapter.getItemViewType(nextPosition) == RvStickyMultiFeedAdapter.TYPE_TIME) {
          View view = linearLayoutManager.findViewByPosition(nextPosition);
          if (view != null) {
            if (view.getTop() <= mSuspensionHeight) {
              mSuspensionBar.setY(-(mSuspensionHeight - view.getTop()));
            } else {
              mSuspensionBar.setY(0);
            }
          }
        }

        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        if (mCurrentPosition != firstVisibleItemPosition) {
          mCurrentPosition = firstVisibleItemPosition;
          mSuspensionBar.setY(0);

          updateSuspensionBar();
        }
      }
    });

    updateSuspensionBar();
  }

  private void updateSuspensionBar() {
    mSuspensionTv.setText(getTime(mCurrentPosition));
  }

  private String getTime(int position) {
    return "NOVEMBER " + (1 + position / 4);
  }
}
