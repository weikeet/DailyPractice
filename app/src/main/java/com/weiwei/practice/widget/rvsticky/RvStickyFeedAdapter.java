package com.weiwei.practice.widget.rvsticky;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.weiwei.practice.R;

/**
 * Created by snowbean on 16-11-4.
 */
public class RvStickyFeedAdapter extends RecyclerView.Adapter<RvStickyFeedAdapter.FeedHolder> {
  private static final String TAG = "FeedAdapter";

  @NonNull
  @Override
  public FeedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.rvsticky_item_feed, parent, false);
    return new FeedHolder(itemView);
  }

  @Override public void onBindViewHolder(FeedHolder holder, int position) {
    Glide.with(holder.itemView.getContext())
        .load(getAvatarResId(position))
        .centerInside()
        .into(holder.mIvAvatar);

    Glide.with(holder.itemView.getContext())
        .load(getContentResId(position))
        .centerInside()
        .into(holder.mIvContent);

    holder.mTvNickname.setText("Taeyeon " + position);
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

  @Override public int getItemCount() {
    return 100;
  }

  public static class FeedHolder extends RecyclerView.ViewHolder {
    ImageView mIvAvatar;
    ImageView mIvContent;
    TextView mTvNickname;

    public FeedHolder(View itemView) {
      super(itemView);
      mIvAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
      mIvContent = (ImageView) itemView.findViewById(R.id.iv_content);
      mTvNickname = (TextView) itemView.findViewById(R.id.tv_nickname);
    }
  }
}
