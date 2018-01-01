package io.weicools.daily.practice.room.databse;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import io.weicools.daily.practice.room.data.CardDataEntity;
import io.weicools.daily.practice.room.data.GetCardItemCallback;

/**
 * Create by weicools on 2017/12/30.
 */

public class CardDbManager {

    @Nullable
    private static CardDbManager INSTANCE = null;

    @Nullable
    private CardDatabase mDb = null;

    private CardDbManager(@NonNull Context context) {
        DatabaseCreator creator = DatabaseCreator.getInstance();
        if (!creator.isDatabaseCreated()) {
            creator.createDb(context);
        }
    }

    public static CardDbManager getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CardDbManager(context);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @SuppressLint("StaticFieldLeak")
    public void getItem(final int itemId, @NonNull final GetCardItemCallback callback) {
        if (mDb == null) {
            mDb = DatabaseCreator.getInstance().getDatabase();
        }

        if (mDb != null) {
            new AsyncTask<Void, Void, CardDataEntity>() {
                @Override
                protected CardDataEntity doInBackground(Void... voids) {
                    return mDb.cardDataDao().queryItemById(itemId);
                }

                @Override
                protected void onPostExecute(CardDataEntity item) {
                    super.onPostExecute(item);
                    if (item == null) {
                        callback.onDataNotAvailable();
                    } else {
                        callback.onItemLoaded(item);
                    }
                }
            }.execute();
        }
    }

    public void favoriteItem(final int itemId, boolean favorite) {
        if (mDb == null) {
            mDb = DatabaseCreator.getInstance().getDatabase();
        }

        if (mDb != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    CardDataEntity tmp = mDb.cardDataDao().queryItemById(itemId);
                    //tmp.setFavorite(favorite);
                    //mDb.zhihuDailyNewsDao().update(tmp);
                }
            }).start();
        }
    }

    public void saveAll(@NonNull final List<CardDataEntity> list) {
        if (mDb == null) {
            mDb = DatabaseCreator.getInstance().getDatabase();
        }

        if (mDb != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mDb.beginTransaction();
                    try {
                        mDb.cardDataDao().insertAll(list);
                        mDb.setTransactionSuccessful();
                    } finally {
                        mDb.endTransaction();
                    }
                }
            }).start();
        }
    }
}
