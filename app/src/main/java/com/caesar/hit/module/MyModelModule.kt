package com.caesar.hit.module

import com.caesar.hit.beans.UserInfo
import com.caesar.hit.beans.VMGlobalData
import com.caesar.hit.utils.CaesarHitLog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */

@InstallIn(ViewModelComponent::class)
@Module
 class MyModelModule {

    @Provides
    fun providerVM(): VMGlobalData {
        return VMGlobalData()
    }

    @Provides
    @ViewModelScoped
    fun providerUserInfo(): UserInfo {
        CaesarHitLog.I("ViewModel的UserInfo创建了")
        return UserInfo()
    }



}