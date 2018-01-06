package io.weicools.daily.practice.room.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.weicools.daily.practice.R;
import io.weicools.daily.practice.room.Injection;
import io.weicools.daily.practice.room.data.CardDataEntity;

public class RoomActivity extends AppCompatActivity {
    @BindView(R.id.rv_card_item)
    RecyclerView mRecyclerView;

    private CardListAdapter mAdapter;
    private CardViewModel mViewModel;
    private ViewModelFactory mViewModelFactory;
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        ButterKnife.bind(this);

        mAdapter = new CardListAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mViewModelFactory = Injection.provideViewModelFactory(this);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CardViewModel.class);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDisposable.clear();
    }

    @OnClick(R.id.btn_room_add)
    public void addDataList() {
        Date date = new Date();
        long smsTime = date.getTime();
        CardDataEntity dataEntity1 = new CardDataEntity("10086",
                "讨债鬼--讨债鬼--讨债鬼--讨债鬼--讨债鬼--讨债鬼--讨债鬼--讨债鬼--讨债鬼--讨债鬼",
                smsTime);

        CardDataEntity dataEntity2 = new CardDataEntity("12306",
                "火车票--火车票--火车票--火车票--火车票--火车票--火车票--火车票--火车票--火车票",
                smsTime);

        List<CardDataEntity> dataEntityList = new ArrayList<>();
        dataEntityList.add(dataEntity1);
        dataEntityList.add(dataEntity2);

        mDisposable.add(mViewModel.insertAllCards(dataEntityList).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        queryData();
                    }
                }));
    }

    @OnClick(R.id.btn_room_query)
    public void queryData() {
        mDisposable.add(mViewModel.getAllCards().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CardDataEntity>>() {
                    @Override
                    public void accept(List<CardDataEntity> cardDataEntities) throws Exception {
                        mAdapter.setDataList(cardDataEntities);
                    }
                }));
    }
}
