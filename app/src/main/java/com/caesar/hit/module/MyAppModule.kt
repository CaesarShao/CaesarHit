package com.caesar.hit.module

import android.content.Context
import com.caesar.hit.beans.*
import com.caesar.hit.utils.CaesarHitLog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */

@InstallIn(SingletonComponent::class)
@Module
 class MyAppModule {



    @Provides
    @Singleton
    fun providerSingleGlobal(): SingleGlobalData {
        return SingleGlobalData()
    }

    @Provides
    fun providerMoreGlobal( singleData: SingleData,@ApplicationContext context: Context): MoreGlobalData {
        return MoreGlobalData(singleData,context)
    }

    @Provides
    @Singleton
    fun providerOne(): OneData {
        CaesarHitLog.I("OneData做些什么")
        return OneData()
    }



}