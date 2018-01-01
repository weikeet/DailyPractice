package io.weicools.daily.practice.room;

import io.reactivex.Flowable;
import io.weicools.daily.practice.room.data.CardDataEntity;
import io.weicools.daily.practice.room.databse.CardDataDao;

/**
 * Create by weicools on 2018/1/1.
 */

public class LocalCardDataSource implements CardDataSource {
    private final CardDataDao mDataDao;

    public LocalCardDataSource(CardDataDao dataDao) {
        mDataDao = dataDao;
    }

    @Override
    public Flowable<CardDataEntity> getCardData() {
        return mDataDao.getCardData();
    }

    @Override
    public void insertOrUpdateCard(CardDataEntity entity) {
        mDataDao.insertCard(entity);
    }

    @Override
    public void deleteAllUsers() {
        mDataDao.deleteAllCard();
    }
}
