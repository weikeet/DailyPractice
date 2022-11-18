/*
 * Copyright (c) 2020 Weiwei
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *    https://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.weiwei.practice.di.sample.di

import android.content.Context
import androidx.room.Room
import com.weiwei.practice.di.sample.data.AppDatabase
import com.weiwei.practice.di.sample.data.LogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author weiwei
 * @date 2022.11.18
 */
// ApplicationComponent is Deprecated in Dagger Version 2.30
// ApplicationComponent removed in Dagger Version 2.31
// Alternatively SingletonComponent should be used instead of ApplicationComponent
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

  @Provides
  @Singleton
  fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "logging.db").build()
  }

  @Provides
  fun provideLogDao(appDatabase: AppDatabase): LogDao {
    return appDatabase.logDao()
  }
}