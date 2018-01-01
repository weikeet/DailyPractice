package io.weicools.daily.practice.room;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.weicools.daily.practice.R;
import io.weicools.daily.practice.room.data.CardDataEntity;

/**
 * Create by weicools on 2018/1/1.
 */

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<CardDataEntity> mDataEntityList;

    public CardListAdapter(Context context, @NonNull List<CardDataEntity> dataList) {
        mContext = context;
        mDataEntityList = dataList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardViewHolder(mLayoutInflater.inflate(R.layout.item_card, parent, false));
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        CardDataEntity dataEntity = mDataEntityList.get(position);

        Glide.with(mContext).load(dataEntity.getLogoAvatar()).fitCenter().into(holder.ivLogo);
        holder.tvTitle.setText(dataEntity.getPhoneNum());
        //holder.tvLastTime.setText(dataEntity.getReceiveTime());
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
