package io.weicools.daily.practice.greenDao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.weicools.daily.practice.R;

public class GreenDaoActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_green_action1)
    Button mBtnGreenAction1;
    @BindView(R.id.btn_green_action2)
    Button mBtnGreenAction2;
    @BindView(R.id.btn_green_action3)
    Button mBtnGreenAction3;
    @BindView(R.id.btn_green_action4)
    Button mBtnGreenAction4;
    @BindView(R.id.rv_shop)
    RecyclerView mRvShop;

    private ShopListAdapter mAdapter;
    private List<Shop> mShopList;
    private static int i = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.bind(this);

        mBtnGreenAction1.setOnClickListener(this);
        mBtnGreenAction2.setOnClickListener(this);
        mBtnGreenAction3.setOnClickListener(this);
        mBtnGreenAction4.setOnClickListener(this);

        queryData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_green_action1:
                addData();
                break;

            case R.id.btn_green_action2:
                deleteData();
                break;

            case R.id.btn_green_action3:
                updateData();
                break;

            case R.id.btn_green_action4:
                queryData();
                break;
            default:
                break;
        }
    }

    private void deleteData() {
        if (!mShopList.isEmpty()) {
            LoveDao.deleteLove(mShopList.get(0).getId());
            queryData();
        }
    }

    private void queryData() {
        mShopList = new ArrayList<>();
        mShopList = LoveDao.queryShop();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvShop.setLayoutManager(layoutManager);
        mAdapter = new ShopListAdapter(this, mShopList);
        mRvShop.setAdapter(mAdapter);
    }

    private void addData() {
        Shop shop = new Shop();
        shop.setType(Shop.TYPE_LOVE);
        shop.setAddress("广东深圳");
        shop.setImage_url("http://blog-1251678165.coscd.myqcloud.com/2018-01-01-083828.jpg");
        shop.setPrice("19.40");
        shop.setSell_num(15263);
        shop.setName("正宗梅菜扣肉 聪厨梅干菜扣肉 家宴常备方便菜虎皮红烧肉 2盒包邮" + i++);
        LoveDao.insertLove(shop);
        queryData();
    }

    private void updateData() {
        if (!mShopList.isEmpty()) {
            Shop shop = mShopList.get(0);
            shop.setName("我是修改的名字");
            LoveDao.updateLove(shop);
            queryData();
        }
    }
}
