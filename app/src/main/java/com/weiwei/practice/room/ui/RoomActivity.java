package com.weiwei.practice.room.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
// import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import com.weiwei.practice.R;
import com.weiwei.practice.room.Injection;
import com.weiwei.practice.room.data.CardDataEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author weicools
 */
public class RoomActivity extends AppCompatActivity {
  RecyclerView mRecyclerView;

  private CardListAdapter mAdapter;
  private CardViewModel mViewModel;
  private final CompositeDisposable mDisposable = new CompositeDisposable();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_room);

    mRecyclerView = findViewById(R.id.rv_card_item);

    findViewById(R.id.btn_room_add).setOnClickListener(v -> addDataList());

    findViewById(R.id.btn_room_query).setOnClickListener(v -> queryData());

    mAdapter = new CardListAdapter(this);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setAdapter(mAdapter);
    ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
    // mViewModel = ViewModelProviders.of(this, viewModelFactory).get(CardViewModel.class);
  }

  @Override
  protected void onStop() {
    super.onStop();
    mDisposable.clear();
  }

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

    mDisposable.add(mViewModel.insertAllCards(dataEntityList)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::queryData));
  }

  public void queryData() {
    mDisposable.add(mViewModel.getAllCards()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(cardDataEntities -> mAdapter.setDataList(cardDataEntities)));
  }
}
