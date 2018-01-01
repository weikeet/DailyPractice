package io.weicools.daily.practice.room;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
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
import io.weicools.daily.practice.greenDao.LoveDao;
import io.weicools.daily.practice.greenDao.Shop;
import io.weicools.daily.practice.greenDao.ShopListAdapter;
import io.weicools.daily.practice.room.data.CardDataEntity;
import io.weicools.daily.practice.room.databse.CardDatabase;
import io.weicools.daily.practice.room.databse.DatabaseCreator;

public class RoomActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_room_add)
    Button mBtnRoomAdd;
    @BindView(R.id.btn_room_delete)
    Button mBtnRoomDelete;
    @BindView(R.id.btn_room_update)
    Button mBtnRoomUpdate;
    @BindView(R.id.btn_room_query)
    Button mBtnRoomQuery;
    @BindView(R.id.rv_card_item)
    RecyclerView mRecyclerView;

    private List<CardDataEntity> mDataList;
    private CardListAdapter mAdapter;
    private static int i = 10;

    @Nullable
    private CardDatabase mDb = null;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        ButterKnife.bind(this);
        mContext = this;

        mBtnRoomAdd.setOnClickListener(this);
        mBtnRoomDelete.setOnClickListener(this);
        mBtnRoomUpdate.setOnClickListener(this);
        mBtnRoomQuery.setOnClickListener(this);

        DatabaseCreator creator = DatabaseCreator.getInstance();
        if (!creator.isDatabaseCreated()) {
            creator.createDb(this);
        }
        mDb = creator.getDatabase();

        queryData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_room_add:
                addData();
                break;
            case R.id.btn_room_delete:
                deleteData();
                break;
            case R.id.btn_room_update:
                updateData();
                break;
            case R.id.btn_room_query:
                queryData();
                break;
            default:
                break;
        }
    }

    private void queryData() {
        mDataList = new ArrayList<>();

        if (mDb == null) {
            mDb = DatabaseCreator.getInstance().getDatabase();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (mDb != null) {
                    mDataList = mDb.cardDataDao().queryAllData();
                }

                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new CardListAdapter(mContext, mDataList);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                });
            }
        }).start();
    }

    private void addData() {
        if (mDb == null) {
            return;
        }

        CardDataEntity dataEntity1 = new CardDataEntity();
        dataEntity1.setLogoAvatar("http://blog-1251678165.coscd.myqcloud.com/2018-01-01-083828.jpg");
        dataEntity1.setPhoneNum("10086");
        dataEntity1.setReceiveTime(1230);
        dataEntity1.setContent("讨债鬼--讨债鬼--讨债鬼--讨债鬼--讨债鬼--讨债鬼--讨债鬼--讨债鬼--讨债鬼--讨债鬼");

        CardDataEntity dataEntity2 = new CardDataEntity();
        dataEntity2.setLogoAvatar("http://blog-1251678165.coscd.myqcloud.com/2018-01-01-083828.jpg");
        dataEntity2.setPhoneNum("12306");
        dataEntity2.setReceiveTime(1230);
        dataEntity2.setContent("火车票--火车票--火车票--火车票--火车票--火车票--火车票--火车票--火车票--火车票");

        final List<CardDataEntity> dataEntityList = new ArrayList<>();
        dataEntityList.add(dataEntity1);
        dataEntityList.add(dataEntity2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                mDb.cardDataDao().insertAll(dataEntityList);
            }
        }).start();

        queryData();
    }

    private void deleteData() {
        if (!mDataList.isEmpty()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mDb.cardDataDao().delete(mDataList.get(0));
                }
            }).start();

            // LoveDao.deleteLove(mDataList.get(0).getId());
            queryData();
        }
    }

    private void updateData() {
        if (!mDataList.isEmpty()) {
            final CardDataEntity entity = mDataList.get(0);
            entity.setPhoneNum("10010 10010");
            entity.setContent("233333333333");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    mDb.cardDataDao().update(entity);
                }
            }).start();

            queryData();
        }
    }
}
