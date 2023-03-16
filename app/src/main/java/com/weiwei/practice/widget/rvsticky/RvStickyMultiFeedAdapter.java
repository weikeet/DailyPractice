package com.weiwei.practice.widget.rvsticky;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.weiwei.practice.R;

/**
 * Created by snowbean on 16-11-21.
 */
public class RvStickyMultiFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  public static final int TYPE_TIME = 0;
  public static final int TYPE_FEED = 1;

  @Override
  public int getItemViewType(int position) {
    if (position % 4 == 0) {
      return TYPE_TIME;
    }
    return TYPE_FEED;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (viewType == TYPE_TIME) {
      View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvsticky_item_time, parent, false);
      return new TimeHolder(itemView);
    } else {
      View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvsticky_item_feed, parent, false);
      return new RvStickyFeedAdapter.FeedHolder(itemView);
    }
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof TimeHolder) {
      ((TimeHolder) holder).mTvTime.setText(getTime(position));
    } else if (holder instanceof RvStickyFeedAdapter.FeedHolder) {
      Glide.with(holder.itemView.getContext())
          .load(getAvatarResId(position))
          .centerInside()
          .into(((RvStickyFeedAdapter.FeedHolder) holder).mIvAvatar);

      Glide.with(holder.itemView.getContext())
          .load(getContentResId(position))
          .centerInside()
          .into(((RvStickyFeedAdapter.FeedHolder) holder).mIvContent);

      ((RvStickyFeedAdapter.FeedHolder) holder).mTvNickname.setText("Taeyeon " + position);
    }
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

  private int getContentResId(int position) {
    switch (position % 4) {
      case 0:
        return R.drawable.rvsticky_taeyeon_one;
      case 1:
        return R.drawable.rvsticky_taeyeon_two;
      case 2:
        return R.drawable.rvsticky_taeyeon_three;
      case 3:
        return R.drawable.rvsticky_taeyeon_four;
    }
    return 0;
  }

  private String getTime(int position) {
    return "NOVEMBER " + (1 + position / 4);
  }

  @Override
  public int getItemCount() {
    return 100;
  }

  public static class TimeHolder extends RecyclerView.ViewHolder {
    TextView mTvTime;

    public TimeHolder(View itemView) {
      super(itemView);
      mTvTime = (TextView) itemView.findViewById(R.id.tv_time);
    }
  }
}
