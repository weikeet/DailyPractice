package io.weicools.daily.practice.room.databse;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Flowable;
import io.weicools.daily.practice.room.data.CardDataEntity;
import java.util.List;

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
