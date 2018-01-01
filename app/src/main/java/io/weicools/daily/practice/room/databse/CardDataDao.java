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
    List<CardDataEntity> queryAllData();

//    @Query("SELECT * FROM card_data WHERE timestamp BETWEEN (:timestamp - 24*60*60*1000 + 1) AND :timestamp ORDER BY timestamp ASC")
//    List<CardDataEntity> queryAllByDate(long timestamp);

    @Query("SELECT * FROM card_data WHERE id = :id")
    CardDataEntity queryItemById(int id);

//    @Query("SELECT * FROM card_data WHERE favorite = 1")
//    List<CardDataEntity> queryAllFavorites();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<CardDataEntity> items);

    @Update
    void update(CardDataEntity item);

    @Delete
    void delete(CardDataEntity item);

    /**
     * Get the user from the table. Since for simplicity we only have one user in the database,
     * this query gets all users from the table, but limits the result to just the 1st user.
     *
     * @return the user from the table
     */
    @Query("SELECT * FROM card_data")
    Flowable<CardDataEntity> getCardData();

    /**
     * Insert a user in the database. If the user already exists, replace it.
     *
     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCard(CardDataEntity user);

    /**
     * Delete all users.
     */
    @Query("DELETE FROM Users")
    void deleteAllCard();
}
