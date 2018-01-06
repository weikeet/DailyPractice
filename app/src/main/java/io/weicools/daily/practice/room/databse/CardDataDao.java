package io.weicools.daily.practice.room.databse;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.weicools.daily.practice.room.data.CardDataEntity;

/**
 * Create by weicools on 2017/12/30.
 * <p>
 * Interface for database access on {@link io.weicools.daily.practice.room.data.CardDataEntity} related operations.
 */

@Dao
public interface CardDataDao {
    @Query("SELECT * FROM card_data")
    Flowable<List<CardDataEntity>> getAllCards();

    @Query("SELECT * FROM card_data WHERE phone_num = :num")
    Flowable<CardDataEntity> getCardByNum(String num);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllCards(List<CardDataEntity> items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCard(CardDataEntity item);

    @Update
    void updateCard(CardDataEntity item);

    @Delete
    void deleteCard(CardDataEntity item);

    @Query("DELETE FROM card_data")
    void deleteAllCards();
}
