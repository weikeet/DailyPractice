package io.weicools.daily.practice.room;

import io.reactivex.Flowable;
import io.weicools.daily.practice.room.data.CardDataEntity;
import io.weicools.daily.practice.room.databse.CardDataDao;

/**
 * Create by weicools on 2018/1/1.
 */

public interface CardDataSource {

    /**
     * Gets the user from the data source.
     *
     * @return the user from the data source.
     */
    Flowable<CardDataEntity> getCardData();

    /**
     * Inserts the user into the data source, or, if this is an existing user, updates it.
     *
     * @param entity the CardDataEntity to be inserted or updated.
     */
    void insertOrUpdateCard(CardDataEntity entity);

    /**
     * Deletes all users from the data source.
     */
    void deleteAllUsers();


}
