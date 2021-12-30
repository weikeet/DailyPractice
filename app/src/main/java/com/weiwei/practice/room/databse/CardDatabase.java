package com.weiwei.practice.room.databse;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.weiwei.practice.room.data.CardDataEntity;

/**
 * Create by weicools on 2017/12/30.
 * <p>
 * card database description.
 */

@Database(entities = { CardDataEntity.class }, version = 1, exportSchema = false)
public abstract class CardDatabase extends RoomDatabase {
  private static volatile CardDatabase INSTANCE;

  public abstract CardDataDao userDao();

  public static CardDatabase getInstance(Context context) {
    if (INSTANCE == null) {
      synchronized (CardDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              CardDatabase.class, "card.db")
              .build();
        }
      }
    }
    return INSTANCE;
  }
}
