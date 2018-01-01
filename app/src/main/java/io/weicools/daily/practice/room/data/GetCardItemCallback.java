package io.weicools.daily.practice.room.data;

import android.support.annotation.NonNull;

/**
 * Create by weicools on 2017/12/30.
 */

public interface GetCardItemCallback {
    void onItemLoaded(@NonNull CardDataEntity item);

    void onDataNotAvailable();
}
