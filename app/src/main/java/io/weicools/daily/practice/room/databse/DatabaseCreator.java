package io.weicools.daily.practice.room.databse;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Create by weicools on 2017/12/30.
 * <p>
 * Create the {@link CardDatabase} asynchronously.
 */

public class DatabaseCreator {
    @Nullable
    private static DatabaseCreator INSTANCE = null;

    private CardDatabase mDb;

    private final AtomicBoolean mInitializing = new AtomicBoolean(true);
    private final AtomicBoolean mIsDbCreated = new AtomicBoolean(false);

    // For Singleton instantiation
    private static final Object LOCK = new Object();

    public synchronized static DatabaseCreator getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new DatabaseCreator();
                }
            }
        }
        return INSTANCE;
    }

    @SuppressLint("StaticFieldLeak")
    public void createDb(Context context) {
        Log.d("DatabaseCreator", "Creating DB from " + Thread.currentThread().getName());
        if (!mInitializing.compareAndSet(true, false)) {
            return;
        }

        new AsyncTask<Context, Void, Void>() {
            @Override
            protected Void doInBackground(Context... contexts) {
                Log.d("DatabaseCreator", "Starting bg job " + Thread.currentThread().getName());

                Context ctx = contexts[0].getApplicationContext();
                mDb = Room.databaseBuilder(ctx, CardDatabase.class, CardDatabase.DATABASE_NAME).build();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mIsDbCreated.set(true);
            }
        }.execute(context.getApplicationContext());
    }

    public boolean isDatabaseCreated() {
        return mIsDbCreated.get();
    }

    @Nullable
    public CardDatabase getDatabase() {
        return mDb;
    }
}
