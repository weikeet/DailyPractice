package io.weicools.daily.practice.room.databse;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import io.weicools.daily.practice.room.data.CardDataEntity;

/**
 * Create by weicools on 2017/12/30.
 * <p>
 * card database description.
 */

@Database(entities = {CardDataEntity.class}, version = 1, exportSchema = false)
public abstract class CardDatabase extends RoomDatabase {
    static final String DATABASE_NAME = "sms-card-db";

    public abstract CardDataDao cardDataDao();
}
