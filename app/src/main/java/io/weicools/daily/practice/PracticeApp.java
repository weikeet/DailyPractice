package io.weicools.daily.practice;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import io.weicools.daily.practice.greenDao.DaoMaster;
import io.weicools.daily.practice.greenDao.DaoSession;

/**
 * Create by weicools on 2018/1/1.
 */

public class PracticeApp extends Application {
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        //配置数据库
        setUpDatabase();
    }

    /**
     * 配置数据库
     */
    private void setUpDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstance() {
        return daoSession;
    }
}
