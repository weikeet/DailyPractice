package io.weicools.daily.practice.room;

import android.content.Context;

import io.weicools.daily.practice.room.data.CardDataSource;
import io.weicools.daily.practice.room.data.LocalCardDataSource;
import io.weicools.daily.practice.room.databse.CardDatabase;
import io.weicools.daily.practice.room.ui.ViewModelFactory;

/**
 * Enables injection of data sources.
 */
public class Injection {

    public static CardDataSource provideUserDataSource(Context context) {
        CardDatabase database = CardDatabase.getInstance(context);
        return new LocalCardDataSource(database.userDao());
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        CardDataSource dataSource = provideUserDataSource(context);
        return new ViewModelFactory(dataSource);
    }
}
