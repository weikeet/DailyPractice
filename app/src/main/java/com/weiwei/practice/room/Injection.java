package com.weiwei.practice.room;

import android.content.Context;
import com.weiwei.practice.room.data.CardDataSource;
import com.weiwei.practice.room.data.LocalCardDataSource;
import com.weiwei.practice.room.databse.CardDatabase;
import com.weiwei.practice.room.ui.ViewModelFactory;

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
