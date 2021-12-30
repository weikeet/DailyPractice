package com.weiwei.practice.room.data;

import io.reactivex.Flowable;
import com.weiwei.practice.room.databse.CardDataDao;
import java.util.List;

/**
 * Create by weicools on 2018/1/1.
 */

public class LocalCardDataSource implements CardDataSource {
  private final CardDataDao mDataDao;

  public LocalCardDataSource(CardDataDao dataDao) {
    mDataDao = dataDao;
  }

  @Override
  public Flowable<List<CardDataEntity>> getAllCards() {
    return mDataDao.getAllCards();
  }

  @Override
  public Flowable<CardDataEntity> getCardByNum(String num) {
    return mDataDao.getCardByNum(num);
  }

  @Override
  public void insertAllCards(List<CardDataEntity> items) {
    mDataDao.insertAllCards(items);
  }

  @Override
  public void insertCard(CardDataEntity item) {
    mDataDao.insertCard(item);
  }

  @Override
  public void updateCard(CardDataEntity item) {
    mDataDao.updateCard(item);
  }

  @Override
  public void deleteCard(CardDataEntity item) {
    mDataDao.deleteCard(item);
  }

  @Override
  public void deleteAllCards() {
    mDataDao.deleteAllCards();
  }
}
