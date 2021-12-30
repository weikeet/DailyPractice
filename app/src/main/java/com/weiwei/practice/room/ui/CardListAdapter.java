package com.weiwei.practice.room.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.weiwei.practice.R;
import com.weiwei.practice.room.data.CardDataEntity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author weicools Create on 2018/1/1.
 */

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardViewHolder> {

  private Context mContext;
  private LayoutInflater mLayoutInflater;
  private List<CardDataEntity> mDataEntityList;

  CardListAdapter(Context context) {
    mContext = context;
    mDataEntityList = new ArrayList<>();
    mLayoutInflater = LayoutInflater.from(context);
  }

  void setDataList(List<CardDataEntity> dataList) {
    mDataEntityList = dataList;
    notifyDataSetChanged();
  }

  @NonNull @Override
  public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new CardViewHolder(mLayoutInflater.inflate(R.layout.item_card, parent, false));
  }

  @Override
  public void onBindViewHolder(CardViewHolder holder, int position) {
    CardDataEntity dataEntity = mDataEntityList.get(position);

    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    String timeText = sdf.format(new Date(dataEntity.getReceiveTime()));

    Glide.with(mContext).load(dataEntity.getLogoAvatar()).into(holder.ivLogo);
    holder.tvTitle.setText(dataEntity.getPhoneNum());
    holder.tvLastTime.setText(timeText);
    holder.tvContent.setText(dataEntity.getContent());
  }

  @Override
  public int getItemCount() {
    return mDataEntityList.size();
  }

  static class CardViewHolder extends RecyclerView.ViewHolder {
    final TextView tvTitle, tvLastTime, tvContent;
    final ImageView ivLogo;

    CardViewHolder(View itemView) {
      super(itemView);

      ivLogo = itemView.findViewById(R.id.iv_logo);
      tvTitle = itemView.findViewById(R.id.tv_title);
      tvLastTime = itemView.findViewById(R.id.tv_last_time);
      tvContent = itemView.findViewById(R.id.tv_content);
    }
  }
}
