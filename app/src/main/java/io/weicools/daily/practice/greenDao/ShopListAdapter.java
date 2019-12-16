package io.weicools.daily.practice.greenDao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import io.weicools.daily.practice.R;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * @author weicools Create on 2018/1/1.
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

  @NotNull @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
    return new ShopViewHolder(mInflater.inflate(R.layout.item_shop1, parent, false));
  }

  @SuppressLint("SetTextI18n") @Override
  public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof ShopViewHolder) {
      ShopViewHolder shopViewHolder = (ShopViewHolder) holder;
      Shop shop = mShopList.get(position);
      Glide.with(mContext).load(shop.getImage_url()).into(shopViewHolder.ivShop);
      shopViewHolder.tvName.setText(shop.getName());
      shopViewHolder.tvPrice.setText(shop.getPrice() + "");
      shopViewHolder.tvPriceDiscount.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
      shopViewHolder.tvSellNum.setText("已售" + shop.getSell_num() + "件");
    }
  }

  @Override
  public int getItemCount() {
    return mShopList.size();
  }

  private class ShopViewHolder extends RecyclerView.ViewHolder {
    private TextView tvName, tvPrice, tvPriceDiscount, tvSellNum;
    private ImageView ivShop;

    ShopViewHolder(View itemView) {
      super(itemView);

      tvName = itemView.findViewById(R.id.tv_name);
      tvPrice = itemView.findViewById(R.id.tv_price);
      tvPriceDiscount = itemView.findViewById(R.id.tv_price_discount);
      tvSellNum = itemView.findViewById(R.id.tv_sell_num);
      ivShop = itemView.findViewById(R.id.iv_shop);
    }
  }
}
