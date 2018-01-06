package io.weicools.daily.practice.room.data;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Create by weicools on 2018/1/1.
 */

public interface CardDataSource {
    Flowable<List<CardDataEntity>> getAllCards();

    Flowable<CardDataEntity> getCardByNum(String num);

    void insertAllCards(List<CardDataEntity> items);

    void insertCard(CardDataEntity item);

    void updateCard(CardDataEntity item);

    void deleteCard(CardDataEntity item);

    void deleteAllCards();
}
