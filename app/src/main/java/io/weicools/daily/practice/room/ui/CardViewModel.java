package io.weicools.daily.practice.room.ui;

import androidx.lifecycle.ViewModel;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.functions.Action;
import io.weicools.daily.practice.room.data.CardDataEntity;
import io.weicools.daily.practice.room.data.CardDataSource;
import java.util.List;

/**
 * View Model for the {@link RoomActivity}
 */
public class CardViewModel extends ViewModel {

  private final CardDataSource mDataSource;

  public CardViewModel(CardDataSource dataSource) {
    mDataSource = dataSource;
  }

  public Flowable<List<CardDataEntity>> getAllCards() {
    return mDataSource.getAllCards();
  }

  public Flowable<CardDataEntity> getCardByNum(String phoneNum) {
    return mDataSource.getCardByNum(phoneNum);
  }

  public Completable insertAllCards(final List<CardDataEntity> items) {
    return Completable.fromAction(new Action() {
      @Override
      public void run() throws Exception {
        mDataSource.insertAllCards(items);
      }
    });
  }

  public Completable insertCard(final CardDataEntity item) {
    return Completable.fromAction(new Action() {
      @Override
      public void run() throws Exception {
        mDataSource.insertCard(item);
      }
    });
  }

  public Completable updateCard(final CardDataEntity item) {
    return Completable.fromAction(new Action() {
      @Override
      public void run() throws Exception {
        mDataSource.updateCard(item);
      }
    });
  }

  public Completable deleteCard(final CardDataEntity item) {
    return Completable.fromAction(new Action() {
      @Override
      public void run() throws Exception {
        mDataSource.deleteCard(item);
      }
    });
  }

  public Completable deleteAllCards() {
    return Completable.fromAction(new Action() {
      @Override
      public void run() throws Exception {
        mDataSource.deleteAllCards();
      }
    });
  }
}
