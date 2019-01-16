package io.weicools.daily.practice.greenDao;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.weicools.daily.practice.R;

/**
 * Create by weicools on 2018/1/1.
 */

public class ShopListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Shop> mShopList;
    private LayoutInflater mInflater;
    private Context mContext;

    public ShopListAdapter(Context context, List<Shop> list) {
        this.mContext = context;
        this.mShopList = list;
        mInflater = LayoutInflater.from(context);
    }

    public void setDataList(List<Shop> dataList) {
        this.mShopList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopViewHolder(mInflater.inflate(R.layout.item_shop1, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ShopViewHolder) {
            ShopViewHolder shopViewHolder = (ShopViewHolder) holder;
            Shop shop = mShopList.get(position);
            Glide.with(mContext).load(shop.getImage_url()).into(shopViewHolder.iv_shop);
            shopViewHolder.tv_name.setText(shop.getName());
            shopViewHolder.tv_price.setText(shop.getPrice() + "");
            shopViewHolder.tv_price_discount.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            shopViewHolder.tv_sell_num.setText("已售" + shop.getSell_num() + "件");
        }
    }

    @Override
    public int getItemCount() {
        return mShopList.size();
    }

    private class ShopViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_price, tv_price_discount, tv_sell_num;
        private ImageView iv_shop;

        ShopViewHolder(View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_price_discount = itemView.findViewById(R.id.tv_price_discount);
            tv_sell_num = itemView.findViewById(R.id.tv_sell_num);
            iv_shop = itemView.findViewById(R.id.iv_shop);
        }
    }
}
